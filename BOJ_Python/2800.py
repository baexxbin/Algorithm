import sys
from itertools import combinations

input = sys.stdin.readline


def findBracket(formula):               # 괄호 짝의 인덱스 찾기
    stack = []
    for i, v in enumerate(formula):
        if v == '(':
            stack.append(i)
        elif v == ')':
            brackets.append((stack.pop(), i))


def removeBracket():                            # 괄호 지우기
    for i in range(1, len(brackets)+1):         # 1~괄호길이까지 지울 괄호 수의 조합 구하기
        for combi in combinations(brackets, i): # i개 지울때 괄호쌍 조합 리스트ㅌ
            tmp = list(formula)
            for idx in combi:
                tmp[idx[0]] = ""
                tmp[idx[1]] = ""
                ans.add(''.join(tmp))


if __name__ == '__main__':
    formula = input().strip()
    brackets = []
    ans = set()

    findBracket(formula)
    removeBracket()

    for i in sorted(ans):
        print(i)


