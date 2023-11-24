def solution(n):
    def hanoi(n, start, target, tmp):
        if n==1:
            answer.append([start, target])
            return
        hanoi(n-1, start, tmp, target)      # n-1를 tmp로
        answer.append([start, target])      # n을 target으로
        hanoi(n-1, tmp, target, start)      # n-1을 target으로

    answer = []
    hanoi(n, 1, 3, 2)
    return answer


n= 3
print(solution(n))

# n-1개를 tmp로 이동
# n을 target으로 이동
# n-1개를 target으로 이동