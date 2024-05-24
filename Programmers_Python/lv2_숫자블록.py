def mxFactor(num):
    if num==1:
        return 0

    tmp = set()
    for x in range(1, int(num**0.5)+1):
        if num % x==0:      # 해당 조건으로 약수를 만족할때, 약수는 나누는 값(x)와 몫 둘 다 해당
            if x<=1e7:      # 나누는 값 x 넣기
                tmp.add(x)
            if num//x <= 1e7 and num//x!=num:   # 나눠진 값 몫 넣기
                tmp.add(num//x)

    return max(tmp)

def solution(begin, end):
    # 특정 구간의 숫자의 약수 중 최대값 구하기 (자기자신 빼고)

    return [mxFactor(i) for i in range(begin, end+1)]


if __name__ == '__main__':
    begin = 1
    end = 10
    print(solution(begin, end))