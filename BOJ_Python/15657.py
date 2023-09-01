import sys

input = sys.stdin.readline


# def backtracking(depth):
#     if depth==M:
#         print(' '.join(map(str, tmp)))
#         return
#
#     for i in range(N):
#         if not tmp:
#             tmp.append(nums[i])
#             backtracking(depth + 1)
#             tmp.pop()
#             continue
#         if nums[i] < tmp[depth-1]:
#             continue
#         tmp.append(nums[i])
#         backtracking(depth+1)
#         tmp.pop()

def backtracking(start, depth):
    if depth==M:
        print(' '.join(map(str, tmp)))
        return

    for i in range(start, N):
        tmp.append(nums[i])
        backtracking(i, depth+1)
        tmp.pop()


if __name__ == '__main__':
    N, M = map(int, input().split())
    nums = sorted(list(map(int, input().split())))

    tmp = []
    backtracking(0, 0)

# 처음 구현한 코드는 코드 중복도 있고 깔끔한 코드는 아님..
# backtracking의 매개변수로 start값도 같이주고, for문의 시작점을 start로 해주어 깔끔하게 구현
# 이때 backtracking으로 넘겨주는 start는 현재 삽입된 값인 i가 된다