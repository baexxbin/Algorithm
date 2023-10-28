# 각 배열의 최대공약수(x,y) 구하기
# 상대 배열을 각 x,y로 나눌 수 없는지 확인
# 두 조건을 만족하는 수 중 가장 큰 수 구하기

def gcd(a, b):
    if b == 0:
        return a
    return gcd(b, a % b)


def solution(arrayA, arrayB):
    gcdA = arrayA[0]
    gcdB = arrayB[0]

    for i in range(1, len(arrayA)):
        gcdA = gcd(gcdA, arrayA[i])
        gcdB = gcd(gcdB, arrayB[i])

    for i in arrayA:
        if i % gcdB == 0:
            gcdB = 1
            break

    for i in arrayB:
        if i % gcdA == 0:
            gcdA = 1
            break

    return 0 if gcdA == gcdB else max(gcdA, gcdB)

# i를 gcd로 나누려면 i%gcd
# 각 배열의 gcd를 구하기위해서 제일 큰 값과 작은값만 비교하는 것이 아닌, 배열의 모든 수 비교
# gcd값이 1이라면 후에 i%gcd==0에서 어짜피 걸러짐