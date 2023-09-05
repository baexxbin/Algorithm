import heapq


def solution(scoville, K):
    heapq.heapify(scoville)
    cnt = 0
    while scoville[0] < K:
        heapq.heappush(scoville, heapq.heappop(scoville) + (heapq.heappop(scoville) * 2))
        cnt += 1

        if len(scoville) == 1 and scoville[0] < K:
            return -1

    return cnt