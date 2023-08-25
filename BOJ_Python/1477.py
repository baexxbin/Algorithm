import sys

input = sys.stdin.readline

# * 처음 설계한 내용 *
# 휴게소 간 거리 구하기
# 제일 긴 거리에 휴게소 짓기
# 휴게소 위치, 가장 긴 거리 업데이트
# 휴게소 m개 지을 때 까지 반복
# >> 반례 존재

# *이분 탐색 parametric search 이용*
# 최적화 문제 : 휴게소를 M개 지을 때 휴게소가 없는 구간의 길이의 최소 길이
# 결정 문제 : 휴게소가 없는 구간의 길이가 x일때 휴게소를 M개 지을 수 있을지

def solve(x):
    cnt = 0
    for i in range(1, len(restStops)):
        cnt += (restStops[i]-restStops[i-1]-1)//x     # 두 휴게소간 거리를 x로 나눌때 세워지는 휴게소 개수, -1하는 이유는 같은 자리 설치 불가
    return cnt > M


if __name__ == '__main__':
    N, M, L = map(int, input().split())
    restStops = list(map(int, input().split()))
    restStops.append(0)
    restStops.append(L)  # 휴게소 시작점과 끝 점도 넣어주기

    restStops.sort()

    st, ed = 1, L-1     # 시작0과 끝L에는 휴게소를 세우지 않음
    ans = 0
    while st <= ed:
        mid = (st+ed)//2
        if solve(mid):  # x로 나눌때 휴게소 갯수가 M보다 많으면
            st = mid+1
        else:
            ed = mid-1
            ans = mid
    print(ans)