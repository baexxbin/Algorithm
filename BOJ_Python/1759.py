import sys

input = sys.stdin.readline


# 조건, 모음 1개 이상, 자음 2개 이상, 오름차순
# 백트래킹으로 암호만들기
# 암호 완성되면 조건 체크 후 올바르다면 출력

def backtracking(idx, cnt):
    if cnt == L:
        tmp = 0
        for c in ans:
            if c in ['a', 'e', 'i', 'o', 'u']:
                tmp += 1

        if tmp >= 1 and L - tmp >= 2:
            print(''.join(ans))
        return

    for i in range(idx, C):             # 백트래킹 현재 idx부터 시작
        ans.append(alpha[i])
        backtracking(i + 1, cnt + 1)    # 다음 idx는 i+1
        ans.pop()


if __name__ == '__main__':
    L, C = map(int, input().split())
    alpha = list(input().split())
    alpha.sort()  # 오름차순 조건을 위한 정렬

    ans = []
    backtracking(0, 0)
