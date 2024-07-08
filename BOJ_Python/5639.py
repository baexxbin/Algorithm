import sys

input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)


# 전위순회로 구성된 A에서 루트, 왼쪽, 오른쪽 분리해서
# 후위순회로 돌리기

def recursive(A):
    if not A:
        return

    node = A[0]
    left, right = [], []

    for i in range(1, len(A)):
        if A[i] > node:
            left = A[1:i]
            right = A[i:]
            break
    else:
        left = A[1:]

    recursive(left)
    recursive(right)
    print(node)


if __name__ == '__main__':
    arr = []
    while True:
        try:
            n = int(input())
            arr.append(n)
        except:
            break

    recursive(arr)
