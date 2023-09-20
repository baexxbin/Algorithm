from itertools import combinations
from collections import Counter


def solution(orders, course):
    ans = []

    # 각 주문별 조합 구하기
    for i in course:
        menu = []
        for order in orders:
            combi = combinations(sorted(order), i)
            menu += combi

        popular = Counter(menu)

        if len(popular) != 0 and max(popular.values()) != 1:
            ans += [''.join(x) for x in popular if popular[x] == max(popular.values())]
    return sorted(ans)

# 초기에 딕셔너리를 이용하려 했으나, Counter가 더 효율적
# combinations에서 sorted()하는 이유: 각 order에서 조합을 만들때 '정렬'로 통일해줘서 'AB', 'BA'와 같은 사태가 일어나지 않도록 함.
# sorted()는 새로운 배열을 반환하나, ans.sort()는 원본 배열을 수정하기에 ans.sort()자체는 null 값 임
