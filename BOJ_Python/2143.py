import bisect
import sys

input = sys.stdin.readline

# 두 배열의 합

if __name__ == '__main__':
    T = int(input())
    N = int(input())
    A = list(map(int, input().split()))
    M = int(input())
    B = list(map(int, input().split()))

    subA, subB = A, B  # 두 배열의 각 부분 합들 미리 구하기
    for i in range(N):
        for j in range(i + 1, N):
            subA.append(sum(A[i:j + 1]))

    for i in range(M):
        for j in range(i + 1, M):
            subB.append(sum(B[i:j + 1]))

    subA.sort()     # A부집합, B부집합 정렬
    subB.sort()

    ans = 0
    for i in range(len(subA)):
        l = bisect.bisect_left(subB, T-subA[i])
        r = bisect.bisect_right(subB, T-subA[i])
        ans += r-l          # 중복되는 b(T-subA[i])의 개수
    print(ans)


# 딕셔너리를 이용하는 방법도 있음 (딕서너리의 원소 엑세스 O(1))