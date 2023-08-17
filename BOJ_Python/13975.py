import heapq
import sys

input = sys.stdin.readline

# 계속 작은 파일들 끼리 합쳐 나감
if __name__ == '__main__':
    T = int(input())

    for _ in range(T):
        K = int(input())
        files = list(map(int, input().split()))

        heapq.heapify(files)  # 리스트 힙으로 변환

        cnt = 0
        while len(files) > 1:
            x = heapq.heappop(files)
            y = heapq.heappop(files)
            tmp = x + y
            cnt += tmp
            heapq.heappush(files, tmp)
        print(cnt)
