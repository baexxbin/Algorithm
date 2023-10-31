from collections import defaultdict


# 두 수의 최소공배수 구하기
# 최소공배수로 두 수를 나누었을때 값이 (1,1), (1,2), (2,3), (3,4) 가 된다면 짝꿍


def solution(weights):
    dic = defaultdict(int)
    cnt = 0
    for i in weights:
        dic[i] += 1

    for key, val in dic.items():
        if val > 1:
            cnt+= val*(val-1) //2       # nC2 조합 공식
        if key*2 in weights:
            cnt+= val*dic[key*2]
        if key*3 % 2 ==0 and key*3//2 in dic:     # 3:2비율 관계, key에 대해 3대2에 해당하는 값이 존재하고, 이게 dic안에 있을때
            cnt+= val*dic[key*3//2]
        if key*4%3==0 and key*4//3 in dic:
            cnt+= val*dic[key*4//3]

    return cnt

# 단순히 몇배 차이나는 애를 구하는 것이아닌, 비율으 가진 key구하기


# def gdc(a, b):
#     if b==0:
#         return a
#     return gdc(b, a%b)
#
#
# def solution(weights):
#     weights.sort()
#     pair = [(1,1), (1,2), (2,3), (3,4)]
#     cnt = 0
#     for i in range(len(weights)-1):
#         for j in range(i+1, len(weights)):
#             val = gdc(weights[i], weights[j])
#             if (weights[i]//val, weights[j]//val) in pair:
#                 cnt+=1
#     print(cnt)

# >> 이 코드는 시간 초과나는 초기 코드


weights = [100,180,360,100,270]
solution(weights)