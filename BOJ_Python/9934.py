import sys

input = sys.stdin.readline


def makeTree(lst, idx):
    if idx<K-1:
        mid = len(lst)//2
        ans[idx].append(lst[mid])
        makeTree(lst[:mid], idx+1)
        makeTree(lst[mid+1:], idx+1)
    else:
        ans[idx].append(lst[-1])


if __name__ == '__main__':
    K = int(input())
    nums = list(map(int, input().split()))
    ans = [[] for _ in range(K)]

    makeTree(nums, 0)

    for a in ans:
        print(*a)



