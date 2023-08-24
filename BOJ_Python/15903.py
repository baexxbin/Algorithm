import heapq
import sys

input = sys.stdin.readline

# 우선순위 큐 이용
# 작은것 부터 뽑고 결과합 다시 우선순위에 넣기
# m번만큼 연산 진행

if __name__ == '__main__':
    n, m = map(int, input().split())
    card = list(map(int, input().split()))
    heapq.heapify(card)     # 기존 리스트 힙으로 변환해주기 (안해주면 정렬안됨)

    while m > 0:
        x = heapq.heappop(card)
        y = heapq.heappop(card)
        z = x + y
        heapq.heappush(card, z)
        heapq.heappush(card, z)
        m -= 1
    print(sum(card))
