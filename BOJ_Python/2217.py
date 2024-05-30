import sys
input = sys.stdin.readline


if __name__ == '__main__':
    N = int(input())
    roap = [int(input()) for _ in range(N)]

    roap.sort()
    mx = 0
    for i in range(N):
        mx = max(mx, roap[i]*(N-i))
    print(mx)

    # 들 수 있는 무게 = (최소로프 무게)*(len(해당 조합의 길이))
    # 즉, 해당 조합에서 최소 로프 무게 * 해당 조합의 길이
    # 각 최소로프무게에서 최대한 조합의 길이가 길수록 높은 값
    # >> 가능한 최대조합의 길이는 정렬했을때 N-idx(자신의 인덱스)