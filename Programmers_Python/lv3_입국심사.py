# mid시간일때 사람을 얼마나 검사할 수 있나(people)
# 검사한 사람의 수에 따라 mid시간 다시 조절

def solution(n, times):
    left = min(times)
    right = n * 987654321

    ans = 0
    while left < right:
        mid = (right + left) // 2

        people = 0
        for i in times:
            people += mid // i
            if people > n:
                break

        if people >= n:
            ans = mid
            right = mid
        else:
            left = mid + 1
    return ans
