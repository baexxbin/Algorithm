def solution(skill, skill_trees):

    cnt = 0
    for word in skill_trees:
        idx = 0
        flag = True
        for w in word:
            if w in skill:
                if skill.index(w) > idx:
                    flag = False
                    break
                idx += 1
        if flag:
            cnt+=1
    print(cnt)





if __name__ == '__main__':
    skill = "CBD"
    skill_trees = ["BACDE", "CBADF", "AECB", "BDA"]

    solution(skill, skill_trees)

    # LST..?
    # 주어진 스킬트리 순서를 지키면서 문자열이 진행되는지
    # skilll모두 안해도 됨