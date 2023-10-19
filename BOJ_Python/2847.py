import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N = int(input())
    level = list(int(input()) for _ in range(N))

    cnt = 0
    for i in range(N-1,0,-1):
        if level[i] <= level[i-1]:
            cnt += level[i-1]-level[i]+1
            level[i-1] = level[i]-1
    print(cnt)
