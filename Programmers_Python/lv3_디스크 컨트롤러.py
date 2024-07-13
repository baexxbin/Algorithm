import heapq
from collections import deque


def solution(jobs):
    jobs.sort()
    que = deque(jobs)
    heap = []
    work = []
    now = 0

    while len(work) != len(jobs):
        while que and que[0][0] <= now:
            cur = que.popleft()
            heapq.heappush(heap, (cur[1], cur[0]))

        if heap:        # 수행할 작업이 있을 경우, 소요시간 작은것부터 시작
            mn = heapq.heappop(heap)
            now += mn[0]
            work.append(now - mn[1])
        else:           # 수행할 작업이 없을 경우, 현재 시간 업데이트
            now += 1
    return int(sum(work) // len(jobs))


if __name__ == '__main__':
    jobs = [[0, 3], [1, 9], [2, 6]]
    solution(jobs)

# 현재 시점에 수행할 요청이 없는경우 처리해줘야함.