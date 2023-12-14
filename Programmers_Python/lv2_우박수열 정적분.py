
def sequence(k):
    seq = []
    while k!=1:
        seq.append(k)
        k = k/2 if k%2==0 else k*3+1
    seq.append(k)
    return seq

def solution(k, ranges):
    ans = []
    seq = sequence(k)
    n = len(seq)
    for a,b in ranges:
        total = 0
        tmp_arr = seq[a:n+b]
        if a >= n+b:
            ans.append(-1)
            continue
        for i in range(len(tmp_arr)-1):     # 사다리꼴 넓이구하기(높이1생략)
            total += (tmp_arr[i]+tmp_arr[i+1])/2
        ans.append(total)
    return ans




k = 5
ranges = [[0,0],[0,-1],[2,-3],[3,-3]]
print(solution(k, ranges))