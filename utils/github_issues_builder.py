#!/usr/bin/env python

from argparse import ArgumentParser
from json import dumps
from requests import session


def _parse_args():
    """ Parses command line arguments to retrieve any optional
    GitHub username and password for establishing connection.

    :returns: Username / token pair parsed from CLI if any.
    """
    parser = ArgumentParser()
    parser.add_argument('--username')
    parser.add_argument('--token')
    parser.add_argument('--repository')
    args = parser.parse_args()
    return args.username, args.token, args.repository


def _get_credentials():
    """ Retrieves GitHub credentials from CLI args
    if any or ask user for it otherwise.

    :returns: Username / password pair as well as repository.
    """
    username, token, repository = _parse_args()
    if username is None:
        username = input('Github username :')
    if token is None:
        token = None # TODO : Use getpassword
    if repository is None:
        repository = input('Github repository :')
    return username, token, repository

_ENDPOINT = 'https://api.github.com/graphql'

class IssuesBuilder(object):
    """ """

    def __init__(self, username, token, repository):
        """ Default constructor.

        :param username: Account username.
        :param password: Account password.
        :param repository: Target repository to build issues for.
        """
        self._username = username
        self._repository = repository
        self._session = session()
        self._session.headers.update({
            'Authorization': 'token %s' % token
        })

    def _is_issue_exist(self, title):
        """ Checks if the issue denoted by the given title exists.

        :param title: Title of the issue to look for.
        :returns: True if the issue already exist, False otherwise.
        """
        data = 'issueCount'
        issue_query = 'search(type: ISSUE, query:"in:title %s") { %s }' % (title, data)
        repository_query = 'repository(owner:"me", name:"%s") { %s }' % (self._repository, issue_query)
        json = { 'query' : repository_query }
        response = self._session.post(_ENDPOINT, json=json)
        print(response.text)


    def _create_issue(self, title, body, labels):
        """
        :param title: Title of the issue to create.
        :param body: Body text of the issue to create.
        :param labels: Labels of the issue to create.
        """
        issue = {
            'title': title,
            'body': body,
            'assignee': self._username,
            'labels': labels
        }
        response = self._session.post(self._endpoint, dumps(issue))
        #if response.status_code:
        if r.status_code == 201:
            print 'Successfully created Issue "%s"' % title
        else:
            print 'Could not create Issue "%s"' % title
            print 'Response:', r.content

#make_github_issue('Issue Title', 'Body text', 'assigned_user', 3, ['bug'])
if __name__ == '__main__':
    username, token, repository = _get_credentials()
    builder = IssuesBuilder(username, token, repository)
    builder._is_issue_exist('testing')
