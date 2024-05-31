import sys

input = sys.stdin.readline


if __name__ == '__main__':
    N, K = map(int, input().split())
    order = list(map(int, input().split()))

    plugs = []
    cnt = 0
    for i in range(K):
        # 이미 꽂혀있으면 패쓰
        if order[i] in plugs:
            continue

        # 꽂혀있지 않을때, 멀티탭이 비어있으면 꽂기
        if len(plugs) < N:
            plugs.append(order[i])
            continue

        # 비어있지 않을 경우 "제일 나중에 사용되는 멀티탭 뽑기"
        later_idx = []
        remains = order[i:]
        for j in range(N):
            if plugs[j] not in remains:
                later_idx.append(101)
                continue
            later_idx.append(remains.index(plugs[j]))
        unplug_idx = later_idx.index(max(later_idx))
        del plugs[unplug_idx]
        plugs.append(order[i])
        cnt+=1

    print(cnt)




