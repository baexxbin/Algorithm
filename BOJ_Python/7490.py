import sys

input = sys.stdin.readline


def dfs(idx, mth):
    if idx == N:
        tmp = eval(mth.replace(' ', ''))
        if tmp == 0:
            res.append(mth)
        return

    nIdx = idx + 1
    dfs(nIdx, mth + ' ' + str(nIdx))
    dfs(nIdx, mth + '+' + str(nIdx))
    dfs(nIdx, mth + '-' + str(nIdx))


if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        N = int(input())
        res = []
        dfs(1, '1')
        for i in res:
            print(i)
        print()
