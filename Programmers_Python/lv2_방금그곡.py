# 15:18 - 16:45

def changeSmall(codes):
    stack = []
    for c in codes:
        if c=='#':
            cur = stack.pop()
            stack.append(cur.lower())
            continue
        stack.append(c)
    return ''.join(stack)

def makeCode(start, end, code):
    m1, s1 = map(int, start.split(':'))
    m2, s2 = map(int, end.split(':'))

    playTime = ((m2 * 60) + s2) - ((m1 * 60) + s1)

    playCode = ''
    ln = len(code)
    if playTime <= ln:
        playCode += code[:playTime]
    else:
        q, r = playTime//ln, playTime%ln
        playCode += code*q+code[:r]

    return playCode, playTime


def solution(m, musicinfos):
    # '#'처리를 위해 #이붙은 부분 소문자로 변경
    m = changeSmall(m)

    # 노래가 재생된 시간만큼 음길이 조절하기

    # 음을 돌면서 m이 들어있는지 확인 (ans배열 삽입)
    # ans배열이 여러개일 경우 조건에 맞는 제목 반환

    ans = []
    idx = 0
    for music in musicinfos:
        start, end, title, code = music.split(',')
        code = changeSmall(code)
        playCode, playTime = makeCode(start, end, code)

        if playCode.find(m) != -1:
            ans.append((playTime, idx, title))

    if not ans:
        return "(None)"
    else:
        ans = sorted(ans, key=lambda x: (-x[0], x[1]))
        return ans[0][-1]




# m = "ABCDEFG"
m = "ABC"
# musicinfos = ["12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"]
musicinfos = ["12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"]
solution(m, musicinfos)


# Node의 반환조건이 "Node"이 아닌, "(None)"이였다..!
# #처리를 위해 스택을 이용했는데 다른 사람들은
# s = s.replace('C#','c').replace('D#','d').replace('F#','f').replace('G#','g').replace('A#','a')
# 위와 같이 replace함수를 사용했다