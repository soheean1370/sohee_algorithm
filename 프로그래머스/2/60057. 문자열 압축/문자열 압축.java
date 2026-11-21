class Solution {
    public int solution(String s) {
        int answer = s.length();

        for (int unit = 1; unit <= s.length() / 2; unit++) {
            int len = 0;   // 압축된 길이
            int i = 0;

            while (i < s.length()) {
                // 남은 글자가 unit보다 적으면 그대로 붙이고 끝
                if (i + unit > s.length()) {
                    len += s.length() - i;
                    break;
                }

                String piece = s.substring(i, i + unit);
                int count = 1;
                // 바로 뒤에 같은 조각이 몇 번 더 나오는지 세기
                while (i + unit * (count + 1) <= s.length()
                        && s.substring(i + unit * count, i + unit * (count + 1)).equals(piece)) {
                    count++;
                }

                if (count > 1) len += String.valueOf(count).length(); // 숫자 자릿수
                len += unit;
                i += unit * count; // 센 만큼 건너뛰기
            }

            answer = Math.min(answer, len);
        }
        return answer;
    }
}