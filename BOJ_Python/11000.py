import heapq
import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N = int(input())
    lecture = [list(map(int, input().split())) for _ in range(N)]

    lecture.sort(key=lambda x: x[0])  # 강의 빨리 끝나는 순서로 정렬
    heap = []
    heapq.heappush(heap, lecture[0][1])  # 첫 시작 강의의 끝나는 시간 넣기

    for i in range(1, N):  # 2번째 강의부터 모든 강의를 돌면서
        if lecture[i][0] < heap[0]:  # 다음강의의 시작이 현재강의가 마치는 시간보다 이르면
            heapq.heappush(heap, lecture[i][1])  # 다음강의가 마치는 시간 넣기 >> heap안의 원소가 필요한 강의장 수!
            continue
        heapq.heappop(heap)  # 현재강의 마친 후 다음강의가 시작 시, 현재 강의는 강의장에서 pop
        heapq.heappush(heap, lecture[i][1])  # 이제 곧 열릴 다음 강의의 끝나는 시간 넣기

    print(len(heap))

# 우선순위 큐 heap의 루트원소 heap[0]은 항상 최소값 유지