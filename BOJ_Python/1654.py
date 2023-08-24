import sys

input = sys.stdin.readline


# 최적화 문제 : N개를 만들 수 있는 랜선의 최대 길이
# 결정 문제 : 랜선의 길이가 X 일때 랜선이 N개 이상인지 아닌지?

# 길이가 X일때 랜선이 N개 이상인지 확인하는 함수
def solve(x):
    cur = 0
    for i in range(K):
        cur += line[i] // x  # 각 랜선을 x로 나누었을 때 해당되는 갯수 카운트
    return cur >= N  # x로 나누었을때 cur개가 N보다 크거나 같으면 True, (좀 더 길이 늘릴 수 있음)


if __name__ == '__main__':
    K, N = map(int, input().split())
    line = list(int(input()) for _ in range(K))

    st, ed = 0, max(line)

    while st < ed:
        mid = (st + ed + 1) // 2    # st와 ed 값이 1 차이날 때 무한루프 방지를 위해 +1 해주기
        if solve(mid):  # mid로 나누었을때 N개가 충족된다면
            st = mid  # 조금 더 길게 잘라보기
        else:  # N개가 안된다면
            ed = mid - 1  # 조금 더 짧게 자르기

    print(st)