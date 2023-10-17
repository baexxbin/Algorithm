import itertools
import re


def solution(expression):

    # expression 숫자랑 기호로 분리하기
    tokens = re.findall(r'(\d+|[\+\-,\*])', expression)
    nums = []
    operator = []
    for t in tokens:
        if t.isdigit():
            nums.append(int(t))
        else:
            operator.append(t)

    # 기호들의 우선순위 순서 정하기
    orders = list(itertools.permutations(['*', '+', '-']))

    ans = []
    # 정해진 순서에 따라 수식 계산하기
    for order in orders:
        tmp_nums = nums[:]            # 미리 구해둔 nus, operator 복사 (값 변경되지 않도록 깊은 복사 진행!!)
        tmp_operator = operator[:]

        for op in order:            # 우선 순위 순서대로 연산
            while op in tmp_operator:   # tmp_operatort에 우선연산자가 남아있는 동안 진행
                idx = tmp_operator.index(op)    # 현재 연산자(op)의 위치 구하기

                if op == '*':
                    v = tmp_nums[idx]*tmp_nums[idx+1]
                elif op == '+':
                    v = tmp_nums[idx] + tmp_nums[idx + 1]
                else:
                    v = tmp_nums[idx] - tmp_nums[idx + 1]

                tmp_nums[idx] = v      # tmp_nums[idx] 연산결과로 업데이트
                tmp_nums.pop(idx+1)    # idx,(idx+1) 합쳐졌으니 idx+1 삭제
                tmp_operator.pop(idx)  # 현재 사용한 연산자도 tmp_operator에서 삭제

        ans.append(abs(tmp_nums[0]))    # 한 우선순위 쌍에 대해 연산다하면 결과 넣기

    return max(ans)



expression = "50*6-3*2"
solution(expression)