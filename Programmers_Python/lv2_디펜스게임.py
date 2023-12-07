import heapq


def solution(n, k, enemy):
    if k >= len(enemy):
        return len(enemy)

    pq = []
    for i in range(len(enemy)):
        heapq.heappush(pq, enemy[i])
        if len(pq) > k:
            fight_enemy = heapq.heappop(pq)
            if fight_enemy > n:
                return i
            n -= fight_enemy
    return len(enemy)


n = 7
k = 3
enemy = [4, 2, 4, 5, 3, 3, 1]
print(solution(n, k, enemy))

# 최대 몇 라운드까지 갈 수 있는지 (남은 병사가 현재 라운드 병사보다 적으면 게임 끝)
# 최대한 병사 조금 만나기 >> 가장 많은 병사에 무적권 사용하기
# 현재 지나온 라운드 중 가장 큰 k값들에 무적권 쓰기
# 우선순위 큐에 현재 적들을 넣으면서, k보다 커지면 최소힙으로 빼서 작은 적부터 싸우기!!