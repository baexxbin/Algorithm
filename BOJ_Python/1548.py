import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, input().split()))

    arr.sort()
    ans = 2 if N > 2 else N

    for x in range(N-2):    # x:x, y:x+1, z:z
        for z in range(N-1, x+1, -1):
            if arr[x]+arr[x+1] > arr[z]:    # x+y>z 조건 만족하면 길이 카운트
                ans = max(ans, z-x+1)
                break

    print(ans)

# z는 y보다 크도록 for문 조건 수정
# 거꾸로 줄어드는 z에서 제일 처음 조건을 만족한 z값이 가장 큰데 왜 z--하는지 이해가 안갔음
    # >> 현재 z에서는 조건을 만족하지 못해 값이 안나올 수 있지만, 다음으로 작은 z에서 조건만족으로 답 나올 수 있음

# 정렬 >> 작은 두수(x,y), 큰 수(z)