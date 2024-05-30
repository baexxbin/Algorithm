from itertools import product


def solution(n, info):
    info.reverse()
    ans = [-1]
    mxDif = 0

    for wl in product((True, False), repeat=11):                            # 중복순열로 모든 경우 구하기
        cnt_r = sum(info[i]+1 for i in range(11) if wl[i])                  # 이때 사용된 화살 수 세기
        if cnt_r<=n:                                                        # 사용된 화살이 주어진 화살보다 작거나 같으면
            apeach = sum(i for i in range(11) if not wl[i] and info[i])     # 어피치, 라이언 각각 점수 구하기 (서로 이긴 점수만 계산)
            ryan = sum(i for i in range(11) if wl[i])
            dif = ryan-apeach                                               # 둘의 점수 차 계산
            if dif > mxDif:                                                 # 최대 점수 차 갱신 됐을때
                mxDif = dif                                                 # 최대 점수 갱신
                ans = [info[i]+1 if wl[i] else 0 for i in range(11)]        # 이때 라이언 점수표 만들기
                ans[0] += n-cnt_r                                           # 사용할 수 있는 화살 다 못 사용했을때 배치
    ans.reverse()
    return ans


if __name__ == '__main__':
    n = 5
    info = [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]
    print(solution(n, info))
