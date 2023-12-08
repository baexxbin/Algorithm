import heapq


def solution(picks, minerals):
    # 곡갱이가 부족할 경우 캘수 있는 광물 만큼 조정
    if sum(picks) * 5 < len(minerals):
        minerals = minerals[:sum(picks) * 5]

    heap = []
    mapping = {"diamond": 25, "iron": 5, "stone": 1}
    value_minerals = [mapping[item] for item in minerals]

    for i in range(0, len(minerals), 5):
        group_sum = sum(value_minerals[i:i + 5])
        heapq.heappush(heap, (-group_sum, i))

    kind = 0
    fatigue = 0
    while sum(value_minerals) != 0:
        cur = heapq.heappop(heap)
        while picks[kind] == 0:     # 주의! 연속으로 도구가 없을 수 있으므로 도구 만날때까지
            kind += 1
        power = 5 ** (2 - kind)
        for i in range(cur[1], min(cur[1] + 5, len(minerals))):
            quo = value_minerals[i] // power
            fatigue += 1 if quo == 0 else quo
            value_minerals[i] = 0
        picks[kind] -= 1
    return fatigue


picks = [0, 0, 1]
minerals = ["diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"]
print(solution(picks, minerals))

# 광물 별 가중치를 매긴다음 광물을 5개씩 묶어서 나타냄
# step1) 캘 수있는 광물 길이 조정
# step2) 광물리스트 점수로 치환 후, 5묶음씩의 함 최대힙 넣기
# step3) 최대값을 우선 뽑은 후, 다이아부터 해결

# 5묶음씩 묶는거에서 너무 시간낭비함,,, 깔끔하게 for문 이용하기


