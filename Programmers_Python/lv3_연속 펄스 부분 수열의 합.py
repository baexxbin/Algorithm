# 펄스를 통해 '가장 큰 부분 수열' 구하기
# 펄스X, 펄스(1), 펄스(-1)에서 각각 최대 부분수열 찾기

def findMx(arr):
    curMx = arr[0]
    toMx = arr[0]

    for i in range(1, len(arr)):
        curMx = max(curMx + arr[i], arr[i])
        if curMx > toMx:
            toMx = curMx

    return toMx


def solution(sequence):
    ans = [0, 0]
    ps1 = [sequence[i] * (1 if i % 2 == 0 else -1) for i in range(len(sequence))]
    ps2 = [sequence[i] * (-1 if i % 2 == 0 else 1) for i in range(len(sequence))]
    ans[0] = findMx(ps1)
    ans[1] = findMx(ps2)

    return max(ans)

# 부분 합 풀이, 가장 큰 부분 합의 차가 최대 값임을 이용
# def solution(sequence):
#     answer = 0
#     prefixS = [0]
#     for i in range(len(sequence)):
#         pulse = 1 if i%2 ==0  else -1
#         prefixS.append(prefixS[-1]+pulse*sequence[i])
#
#     return abs(max(prefixS) - min(prefixS))
