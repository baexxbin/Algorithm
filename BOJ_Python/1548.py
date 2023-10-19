import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, input().split()))

    arr.sort()

    if N < 3:
        print(N)
    else:
        ans = -1
        for x in range(N-2):    # x:x, y:x+1, z:z
            for z in range(N-1, -1, -1):
                if x+1 > z:     # y가 z보다 크면 z값 역전된 것으로 패쓰
                    continue
                if arr[x]+arr[x+1] > arr[z]:    # x+y>z 조건 만족하면 길이 늘리기
                    ans = max(ans, z-x+1)
        print(ans)