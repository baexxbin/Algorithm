import heapq
import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N, K = map(int, input().split())
    jewels = []
    for _ in range(N):
        heapq.heappush(jewels, tuple(map(int, input().split())))
    bags = [int(input()) for _ in range(K)]
    bags.sort()

    ans = 0
    tmp = []
    for bag in bags:
        while jewels:  # 작은 가방부터 확인하면서, 작은 무게 보석 넣을 수 있으면 넣기
            if bag >= jewels[0][0]:
                heapq.heappush(tmp, -heapq.heappop(jewels)[1])  # 무게가 허용되는 한 가능한 보석들을 넣었을때 heapq임으로 최대힙으로 저장됨
            else:
                break
        if tmp:
            ans += -heapq.heappop(tmp)

    print(ans)
