from itertools import combinations


def solution(relation):
    row = len(relation)
    col = len(relation[0])

    # 조합을 위한 조합가능 인덱스 추출
    combi = []
    for i in range(1, col+1):
        combi.extend(combinations(range(col), i))

    # 유일성
    unique = []
    for i in combi:
        tmp = [tuple(item[key] for key in i) for item in relation]
        if len(set(tmp)) == row:
            unique.append(i)

    # 최소성
    answer = set(unique)
    for i in range(len(unique)):
        for j in range(i+1,len(unique)):
            if len(unique[i]) == len(set(unique[i]) & set(unique[j])):
                answer.discard(unique[j])
    return len(answer)




relation = [["100", "ryan", "music", "2"], ["200", "apeach", "math", "2"], ["300", "tube", "computer", "3"],
            ["400", "con", "computer", "4"], ["500", "muzi", "music", "3"], ["600", "apeach", "music", "2"]]
solution(relation)
