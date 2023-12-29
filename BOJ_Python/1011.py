import sys, math

input = sys.stdin.readline


def solve(distance):
    root = math.sqrt(distance)
    if root%1==0:
        return 2*int(root)-1

    nxt = math.ceil(root)
    if distance > math.pow(nxt, 2) - nxt:
        return 2*int(nxt)-1

    return 2*int(nxt)-2


if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        x, y = map(int, input().split())

        print(solve(y-x))