import heapq
import sys

input = sys.stdin.readline

# 큐: 강의실의 개수
# 현재 강의의 '시작'이 이전 강의 '끝' 보다 작으면 수업 겹침 >> 새로운 강의장 필요
# 강의 끝나면 강의장 비워주기 >> 큐에 넣을때 '강의 끝이 작은거 순으로 넣기'

if __name__ == '__main__':
    N = int(input())
    subject = [tuple(map(int, input().split())) for _ in range(N)]

    # 강의 시작순으로 정렬
    subject.sort()

    room = []
    heapq.heappush(room, subject[0][1])      # [0]:강의 끝, [1]:강의 시작

    for cur in range(1, N):
        if subject[cur][0] < room[0]:                 # 현재 강의 시작이 가장 빨리 끝나는 강의 마치는 시간보다 빠르면(작으면)
            heapq.heappush(room, subject[cur][1])        # 새로운 강의장 추가
        else:                                            # 현재 강의가 가장 빨리 끝나는 강의 마치는 시간과 같거나 느림
            heapq.heappushpop(room, subject[cur][1])     # 제일 빨리 끝나는 강의장 비워줌

    print(len(room))

# 리펙토링 >> room에는 강의 시작시간은 넣지 않아도 됨.