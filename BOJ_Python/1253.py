import sys


input = sys.stdin.readline

# 9:20 - 10:00 (혼자 풀기) + 20(다른 코드 참고)

# *초기 구현*
# arr정렬 후 투포인터 형태로 target값을 만들 수 있는지 찾아간다
# 이때 중복되는 수는 어떻게 처리? >> 갯수 세서 곱하기
# - 중복된 수에 대한 st,ed처리가 안됨 (ex, 0,0,0,0 에서 0,1번 인덱스의0은 인식못함)
    # >> biset으로 st,ed 정의, 하지만 이또한 인덱스 오류남..(target포함해서 수행돼서)

# 초기에 생각한 접근이 맞았지만 bisect, Counter 까지 사용할 필요가 없다
# 핵심 아이디어는 중복처리를 위해 'tmp_arr = arr[:i] + arr[i+1:]' 와 같이 배열을 만들어주면 된다..!
# 나(i) 전,후 값 부터가 아닌, 나를 제외한 끝까지 값을 포함 >> 음수 포함으로, 나보다 뒤에 있는 값과도 답이 나올 수 있음

# 위 내용처럼 tmp_arr를 사용하지 못하는 다른 언어에서는 '자기자신을 더하지 않기위해'
# st==i : st++, ed==i : ed--로 예외처리해준다.


if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, input().split()))

    arr.sort()

    cnt = 0
    for i in range(N):
        tmp_arr = arr[:i] + arr[i+1:]
        st, ed = 0, len(tmp_arr)-1
        while st < ed:
            value = tmp_arr[st]+tmp_arr[ed]
            if value == arr[i]:
                cnt+=1
                break
            if value > arr[i]:
                ed -= 1
            else:
                st += 1
    print(cnt)



