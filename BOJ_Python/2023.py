import sys

input = sys.stdin.readline

# 소수판별
def isPrime(num):
    for i in range(2, int(num**0.5+1)):
        if num%i==0:
            return False
    return True


# 소수에 소수 붙이기
def dfs(num):
    if len(str(num)) == n:  # num이 n자리일 경우 출력
        print(num)
        return
    for i in range(1,10):   # 숫자 붙이기
        if i%2==0:          # 짝수는 소수 아니므로 패쓰
            continue
        tmp = num*10+i
        if isPrime(tmp):    # 붙인 숫자도 소수면 또 붙이기(dfs)
            dfs(tmp)


if __name__ == '__main__':
    n = int(input())
    # 소수의 시작: 2,3,5,7부터 소수 붙여 나가기
    dfs(2)
    dfs(3)
    dfs(5)
    dfs(7)
