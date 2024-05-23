import sys

input = sys.stdin.readline

# 가장 빨리 도착하는 마을 순으로 정렬
# 목표 마을까지 가는 동안의 마을의 최대 적재 용량 갱신 (먼저 도착하는 마을 순으로 정렬되어있어서 가능)

if __name__ == '__main__':
    N, C = map(int, input().split())
    M = int(input())

    info = []
    for _ in range(M):
        a, b, c = map(int, input().split())
        info.append((a,b,c))
    info.sort(key=lambda x:x[1])    # 먼저 도착하는 마을 순으로 정렬

    truck = [C for _ in range(N + 1)]   # 각 마을에서 적재할 수 있는 용량 C로 초기화
    total = 0

    # print(info)
    for s, r, box in info:
        mn = C
        for i in range(s, r):           # 실을 수 있는 box값은 일정, 각 마을의 적재용량만 다름
            if mn > min(truck[i], box):
                mn = min(truck[i], box)
        # print(f's:{s}, r:{r}, mn: {mn}')
        for i in range(s, r):
            truck[i] -= mn
        total += mn
        # print(f'truck: {truck}')
    print(total)

    # info = defaultdict(list)
    # for _ in range(M):
    #     a, b, c = map(int, input().split())
    #     info[a].append((b,c))
    #
    # # 마을 번호가 빠른 순으로 짐 싣기 (짐은 나눠 실을 수 있으므로 항상 최대값 유지) >> 앞 번호 기준 정렬
    #
    # truck = [0 for _ in range(N+1)]
    # check = C   # 짐 실을 수 있는 양 나타냄
    # total = 0
    # for i in range(1, N+1):
    #     # 짐 내리기
    #     check += truck[i]
    #     truck[i] = 0
    #     print(f'check {check}, i:{i}')
    #     print(f'truck {truck}')
    #     # 짐 싣기
    #     info[i].sort(key=lambda x:(x[0], x[1]))
    #     print(info[i])
    #     for idx, val in info[i]:
    #         print(f'idx:{idx}, val:{val}, check: {check}')
    #         if check >= val:
    #             truck[idx] += val
    #             check -= val
    #             total += val
    #         elif check != 0 and check < val:
    #             truck[idx] += check
    #             total += check
    #             check = 0
    #     print(f'check2 {check}')
    #     print(f'truck2 {truck}')
    #
    #
    # print(truck)
    # print(sum(truck))
    # print(total)
