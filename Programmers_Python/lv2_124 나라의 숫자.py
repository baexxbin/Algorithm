# 처음 코드 35분 걸림, 테스트 케이스는 통과하나 효율성에서 실패 함
# 50,000,000 * 3 = 1억5천으로 안됨..!

# def solution(n):
#     if n <= 3:
#         return "4" if n == 3 else str(n)
#
#     dp = [0] * (n + 1)
#     dp[1], dp[2], dp[3] = '1', '2', '4'  # dp 초기값 설정
#     idx = 4
#
#     for i in range(1, n + 2):
#         for j in ['1', '2', '4']:
#             if idx < len(dp):
#                 dp[idx] = str(dp[i]) + j
#                 idx += 1
#     return dp[-1]



# 3진법으로 구하기
# 3진법이라는 것은 알고있었지만 이상한 규칙으로 풀려고 했었다..
# 진법 변환 풀이를 이용하면 핵 간단하다...!

def solution(n):
    ans = ''
    while n:
        if n % 3:  # 모듈러 3의 값이 1,2이면 1,2
            ans += str(n % 3)
            n = n // 3
            continue
        ans += '4'
        n = (n // 3) - 1

    return ans[::-1]

