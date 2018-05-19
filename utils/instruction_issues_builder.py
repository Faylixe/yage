#!/usr/bin/env python

""" Simple python script that generates issues for each instruction. """

import re

from argparse import ArgumentParser
from json import dumps
from requests import session, get
from time import sleep

# Target owner
_OWNER = 'Faylixe'

# Target repository.
_REPOSITORY = 'yage'

# GitHub v3 issues endpoint.
_ENDPOINT = 'https://api.github.com/repos/%s/%s/issues' % (_OWNER, _REPOSITORY)

# GitHub v3 search endpoint.
_SEARCH = 'https://api.github.com/search/issues?q='

# URL of the page to parse for opcodes.
_OPCODES = 'http://www.devrs.com/gb/files/opcodes.html'

# Regex used to extract OPCODE from HTML.
_PATTERN = '<TD[^>]*COLSTART="1"[^>]*>([^<]*)</TD>'


def _parse_args():
    """ Parses command line arguments to retrieve authorization token.

    :returns: token parsed from CLI if any.
    """
    parser = ArgumentParser()
    parser.add_argument('--token')
    args = parser.parse_args()
    return args.token


def _get_opcodes():
    """ Downloads HTML page containing all OPCODE and extract them.

    :returns: List of OPCODE extracted.
    """
    html = get(_OPCODES).text
    normalize = lambda x: re.sub('[\s]{2,}', ' ', re.sub('[\s+]', ' ', x))
    instructions = [normalize(i) for i in re.findall(_PATTERN, html)]
    opcodes = []
    for instruction in instructions:
        prefix = instruction[:2]
        if len(prefix) == 2 and prefix == prefix.upper() and prefix not in ('NC', 'NZ'):
            opcodes.append(instruction)
    return opcodes

def _is_issues_exist(session, title):
    """
    """
    query = '%s+user:%s+repo:%s+in:title' % (title, _OWNER, _REPOSITORY)
    response = session.get('%s%s' % (_SEARCH, query))
    if response.status_code != 200:
        return False
    json = response.json()
    return json['total_count'] != 0

def create_issue(session, title, labels):
    """ Creates an issues for target repository.

    :param session:
    :param title:
    :param labels:
    """
    if _is_issues_exist(session, title):
        print('\t Issue already exist, abort')
        return
    issue = {
        'title': title,
        'assignee': _OWNER,
        'labels': labels
    }
    response = session.post(_ENDPOINT, dumps(issue))
    if response.status_code != 201:
        print('\tError when creating issue %s (%s) : %s' % (title, response.status_code, response.text))

if __name__ == '__main__':
    token = _parse_args()
    if token is None:
        raise ValueError('No authorization token provided')
    with session() as session:
        session.headers.update({'Authorization': 'token %s' % token})
        for opcode in _get_opcodes():
            print('> Creating issues for instruction %s' % opcode)
            create_issue(session, 'Implements %s' % opcode, ['instruction'])
            # Search limit : 30 request per minute => one request every 2 seconds.
            sleep(2)
            create_issue(session, 'Tests %s' % opcode, ['instruction', 'testing'])
            sleep(2)
