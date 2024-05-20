def solution(targets):
    targets.sort(key= lambda x: (x[1], x[0]))
    last_end, cnt = 0, 0
    for st, ed in targets:
        if last_end <=st :      # 직전종료시간이 새로운 미사일의 시작시간보다 작거나 같으면 갱신
            cnt+=1
            last_end = ed
    return cnt


if __name__ == '__main__':
    targets = [[4,5],[4,8],[10,14],[11,13],[5,12],[3,7],[1,4]]
    print(solution(targets))


# 어떤 좌표 범위 주어졌을때 겹치는 구간 구하기.
# 겹치지 않는 강의실 문제와 비슷 >> 우선순위 큐 이용?
    # 활동 선택 문제
    # 가장 많이 선택할 수 있도록 마치는 시간이 빠른 순으로 정렬 후 선택