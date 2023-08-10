import sys

input = sys.stdin.readline

if __name__ == '__main__':
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]

    # ab, cd 될 수 있는 모든 조합 넣기 (ab, cd로 뭉쳐서 투포인터로 구하기)
    ab, cd = [], []
    for i in range(N):
        for j in range(N):
            ab.append(arr[i][0]+arr[j][1])
            cd.append(arr[i][2]+arr[j][3])

    # ab, cd 값 투 포인터로 합이 0인 값 찾기
    ab.sort()
    cd.sort()

    cnt = 0
    left, right = 0, len(cd)-1
    while left < len(ab) and right >=0:
        if ab[left]+cd[right]==0:
            tmpL, tmpR = left+1, right-1
            while tmpL < len(ab) and ab[tmpL]==ab[left]:    # 같은 값 중복될 때, 중복 되는 만큼 인덱스 이동
                tmpL+=1
            while tmpR >=0 and cd[tmpR]==cd[right]:
                tmpR-=1
            cnt += (tmpL-left) * (right-tmpR)
            left, right = tmpL, tmpR

        elif ab[left]+cd[right] < 0:
            left+=1
        else:
            right-=1
    print(cnt)


