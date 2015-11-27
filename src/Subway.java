import java.util.Scanner;
// 내일은 레이아웃과 다른 호선
public class Subway {
	public static void main(String [] args) {
        Scanner scan1 = new Scanner(System.in);      // 문자 입력을 인자로 Scanner 생성
		System.out.println("출발역 : ");
		String start = scan1.nextLine();
		
		Scanner scan2 = new Scanner(System.in);
		System.out.println("도착역 : ");
		String end	= scan2.nextLine();
		
		Search di = new Search();	//최단노선 검색 객체 생성
		di.s_count=0;				//거처간 정거장 수 초기화
		
		int time = di.Dijkstra(di.searchStation(start), di.searchStation(end));//다익스트라 알고리즘으로 역 탐색
		int i,j=0;
		if(time==0 || time==30000) {
			System.out.println("\n\n\n      잘못입력하였습니다!!\n\n      역을 제대로 입력하십시오.");
		}
		else {
			System.out.println("\n    <최소시간>\n\n");
			System.out.println("  소요시간 :  약 " +  time + "분\n\n");
			System.out.println("  정거장수 : " +   di.s_count     + "\n\n");
			System.out.println("  경        로  \n\n"); 
			for(i=0; di.V[i].dist < 30000; i++){
				System.out.println(" " + di.V[i].sName + " ->");
				j = i%3;
				if(j == 2) System.out.println("\n");
			}
			System.out.println("  " + di.V[i].sName);
	
		}
	}
}
class SubType {
	String sName = new String();
	int dist;
	int pNode;
	boolean visited;
	int next;
	NodeType link;
}

class NodeType {
	int nodeNum;
	int dis;
	NodeType next;
}

class Search
{
	public static final int MAX_STATIONS	= 400; // 서울시 지하철 노선의 역 수는 약 400개
	public static int stationCount = 1;// 역 수
	public static SubType[] S = new SubType[MAX_STATIONS];	// 지하철 인접 리스트
	public static SubType[] V = new SubType[MAX_STATIONS];	// 결과를 저장
	public static int s_count=0;//거처간 정거장 수

	public Search() {
			for(int i=0; i < MAX_STATIONS; i++) {
				S[i] = new SubType();
				V[i] = new SubType();

			}
			//1호선
			insert("소요산","동두천",4);
			insert("동두천","보산",2);
			insert("보산","동두천중앙",2);
			insert("동두천중앙","지행",2);
			insert("지행","덕정",5);
			insert("덕정","덕계",6);
			insert("덕계","양주",3);
			insert("양주","녹양",3);
			insert("녹양","가능",2);
			insert("가능","의정부",3);
			insert("의정부","회룡",3);
			insert("회룡","망월사",3);
			insert("망월사","도봉산",3);
			insert("도봉산","도봉",2);
			insert("도봉","방학",2);
			insert("방학","창동",2);
			insert("창동","녹천",2);
			insert("녹천","월계",3);
			insert("월계","광운대",3);
			insert("광운대","석계",2);
			insert("석계","신이문",2);
			insert("신이문","외대앞",1);
			insert("외대앞","회기",2);
			insert("회기","청량리",3);
			insert("청량리","제기동",2);
			insert("제기동","신설동",2);
			insert("신설동","동묘앞",2);
			insert("동묘앞","동대문",2);
			insert("동대문","종로5가",2);
			insert("종로5가","종로3가",2);
			insert("종로3가","종각",1);
			insert("종각","시청",3);
			insert("시청","서울역",2);
			insert("서울역","남영",3);
			insert("남영","용산",2);
			insert("용산","노량진",3);
			insert("노량진","대방",2);
			insert("대방","신길",2);
			insert("신길","영등포",2);
			insert("영등포","신도림",3);
			insert("신도림","구로",1);
			insert("구로","구일",2);
			insert("구일","개봉",3);
			insert("개봉","오류동",2);
			insert("오류동","온수",3);
			insert("온수","역곡",2);
			insert("역곡","소사",3);
			insert("소사","부천",2);
			insert("부천","중동",2);
			insert("중동","송내",2);
			insert("송내","부개",3);
			insert("부개","부평",2);
			insert("부평","백운",3);
			insert("백운","동암",2);
			insert("동암","간석",3);
			insert("간석","주안",2);
			insert("주안","도화",1);
			insert("도화","제물포",1);
			insert("제물포","도원",2);
			insert("도원","동인천",2);
			insert("동인천","인천",4);
			
			insert("구로","가산디지털단지",5);
			insert("가산디지털단지","독산",4);
			insert("독산","금천구청",3);
			insert("금천구청","광명",5);
			
			insert("금천구청","석수",3);
			insert("석수","관악",2);
			insert("관악","안양",3);
			insert("안양","명학",3);
			insert("명학","금정",3);
			insert("금정","군포",3);
			insert("군포","당정",1);
			insert("당정","의왕",2);
			insert("의왕","성균관대",4);
			insert("성균관대","화서",3);
			insert("화서","수원",4);
			insert("수원","세류",4);
			insert("세류","병점",4);
			insert("병점","서동탄",3);
			
			insert("병점","세마",4);
			insert("세마","오산대",4);
			insert("오산대","오산",3);
			insert("오산","진위",4);
			insert("진위","송탄",4);
			insert("송탄","서정리",3);
			insert("서정리","지제",4);
			insert("지제","평택",4);
			insert("평택","성환",7);
			insert("성환","직산",5);
			insert("직산","두정",4);
			insert("두정","천안",3);
			insert("천안","봉명",2);
			insert("봉명","쌍용",2);
			insert("쌍용","아산",2);
			insert("아산","배방",4);
			insert("배방","온양온천",4);
			insert("온양온천","신창",4);
			//2호선
			insert("신설동","용두",2);
			insert("용두","신답",2);
			insert("신답","용답",2);
			insert("용답","성수",3);
			insert("성수","건대입구",2);
			insert("건대입구","구의",3);
			insert("구의","강변",1);
			insert("강변","잠실나루",2);
			insert("잠실나루","잠실",3);
			insert("잠실","신천",2);
			insert("신천","종합운동장",2);
			insert("종합운동장","삼성",2);
			insert("삼성","선릉",2);
			insert("선릉","역삼",2);
			insert("역삼","강남",2);
			insert("강남","교대",2);
			insert("교대","서초",2);
			insert("서초","방배",2);
			insert("방배","사당",2);
			insert("사당","낙성대",2);
			insert("낙성대","서울대입구",3);
			insert("서울대입구","봉천",2);
			insert("봉천","신림",2);
			insert("신림","신대방",2);
			insert("신대방","구로디지털단지",2);
			insert("구로디지털단지","대림",2);
			insert("대림","신도림",3);
			
			insert("신도림","도림천",2);
			insert("도림천","양천구청",3);
			insert("양천구청","신정네거리",4);
			insert("신정네거리","까치산",3);
			
			insert("신도림","문래",2);
			insert("문래","영등포구청",2);
			insert("영등포구청","당산",2);
			insert("당산","합정",4);
			insert("합정","홍대입구",2);
			insert("홍대입구","신촌",2);
			insert("신촌","이대",2);
			insert("이대","아현",2);
			insert("아현","충정로",2);
			insert("충정로","시청",3);
			insert("시청","을지로입구",2);
			insert("을지로입구","을지로3가",2);
			insert("을지로3가","을지로4가",1);
			insert("을지로4가","동대문역사문화공원",2);
			insert("동대문역사문화공원","신당",2);
			insert("신당","상왕십리",2);
			insert("상왕십리","왕십리",2);
			insert("왕십리","한양대",2);
			insert("한양대","뚝섬",2);
			insert("뚝섬","성수",1);
			//3호선
			insert("대화","주엽",2);
			insert("주엽","정발산",3);
			insert("정발산","마두",2);
			insert("마두","백석",2);
			insert("백석","대곡",4);
			insert("대곡","화정",3);
			insert("화정","원당",3);
			insert("원당","원흥",3);
			insert("원흥","삼송",3);
			insert("삼송","지축",3);
			insert("지축","삼송",3);
			insert("삼송","지축",3);
			insert("지축","구파발",4);
			insert("구파발","연신내",2);
			insert("연신내","불광",2);
			insert("불광","녹번",2);
			insert("녹번","홍제",3);
			insert("홍제","무악재",2);
			insert("무악재","독립문",2);
			insert("독립문","경복궁",2);
			insert("경복궁","안국",2);
			insert("안국","종로3가",2);
			insert("종로3가","을지로3가",2);
			insert("을지로3가","충무로",1);
			insert("충무로","동대입구",3);
			insert("동대입구","약수",3);
			insert("약수","금호",2);
			insert("금호","옥수",1);
			insert("옥수","압구정",2);
			insert("압구정","신사",2);
			insert("신사","잠원",3);
			insert("잠원","고속터미널",2);
			insert("고속터미널","교대",2);
			insert("교대","남부터미널",2);
			insert("남부터미널","양재",3);
			insert("양재","매봉",2);
			insert("매봉","도곡",2);
			insert("도곡","대치",2);
			insert("대치","학여울",1);
			insert("학여울","대청",2);
			insert("대청","일원",2);
			insert("일원","수서",3);
			insert("수서","가락시장",3);
			insert("가락시장","경찰병원",2);
			insert("경찰병원","오금",1);
			//4호선
			insert("당고개","상계",2);
			insert("상계","노원",2);
			insert("노원","창동",2);
			insert("창동","쌍문",2);
			insert("쌍문","수유",3);
			insert("수유","미아",2);
			insert("미아","미아사거리",2);
			insert("미아사거리","길음",2);
			insert("길음","성신여대입구",3);
			insert("성신여대입구","한성대입구",2);
			insert("한성대입구","혜화",2);
			insert("혜화","동대문",2);
			insert("동대문","동대문역사문화공원",2);
			insert("동대문역사문화공원","충무로",2);
			insert("충무로","명동",1);
			insert("명동","회현",2);
			insert("회현","서울역",2);
			insert("서울역","숙대입구",2);
			insert("숙대입구","삼각지",2);
			insert("삼각지","신용산",1);
			insert("신용산","이촌",2);
			insert("이촌","동작",4);
			insert("동작","총신대입구",3);
			insert("총신대입구","사당",2);
			insert("사당","남태령",1);
			insert("남태령","선바위",3);
			insert("선바위","경마공원",3);
			insert("경마공원","대공원",2);
			insert("대공원","과천",2);
			insert("과천","정부과천첨사",3);
			insert("정부과천첨사","인덕원",2);
			insert("인덕원","평촌",2);
			insert("평촌","범계",2);
			insert("범계","금정",3);
			insert("금정","산본",4);
			insert("산본","수리산",2);
			insert("수리산","대야미",3);
			insert("대야미","반월",3);
			insert("반월","상록수",4);
			insert("상록수","한대앞",2);
			insert("한대앞","중앙",2);
			insert("중앙","고잔",2);
			insert("고잔","초지",3);
			insert("초지","안산",4);
			insert("안산","길온천",2);
			insert("길온천","오이도",2);
			//5호선
			insert("방화","개화산",2);
			insert("개화산","김포공항",3);
			insert("김포공항","송정",3);
			insert("송정","마곡",2);
			insert("마곡","발산",3);
			insert("발산","우장산",2);
			insert("우장산","화곡",2);
			insert("화곡","까치산",2);
			insert("까치산","신정",3);
			insert("신정","목동",2);
			insert("목동","오목교",3);
			insert("오목교","양평",2);
			insert("양평","영등포구청",2);
			insert("영등포구청","영등포시장",2);
			insert("영등포시장","신길",2);
			insert("신길","여의도",2);
			insert("여의도","여의나루",2);
			insert("여의나루","마포",3);
			insert("마포","공덕",3);
			insert("공덕","애오개",2);
			insert("애오개","충정로",2);
			insert("충정로","서대문",2);
			insert("서대문","광화문",2);
			insert("광화문","종로3가",2);
			insert("종로3가","을지로4가",1);
			insert("을지로4가","동대문역사문화공원",2);
			insert("동대문역사문화공원","청구",2);
			insert("청구","신금호",2);
			insert("신금호","행당",2);
			insert("행당","왕십리",2);
			insert("왕십리","마장",2);
			insert("마장","답십리",2);
			insert("답십리","장한평",3);
			insert("장한평","군자",2);
			insert("군자","아치산",3);
			insert("아치산","광나루",3);
			insert("광나루","천호",3);
			insert("천호","강동",2);
			insert("강동","길동",2);
			insert("길동","굽은다리",2);
			insert("굽은다리","명일",2);
			insert("명일","고덕",3);
			insert("고덕","상일동",3);
			
			insert("강동","둔촌동",3);
			insert("둔촌동","올림픽공원",3);
			insert("올림픽공원","방이",2);
			insert("방이","오금",2);
			insert("오금","개롱",2);
			insert("개롱","거여",2);
			insert("거여","마천",2);
			//6호선
			insert("봉화산","화랑대",2);
			insert("화랑대","태릉입구",2);
			insert("태릉입구","석계",2);
			insert("석계","돌곶이",2);
			insert("돌곶이","상월곡",2);
			insert("상월곡","월곡",2);
			insert("월곡","고려대",3);
			insert("고려대","안암",3);
			insert("안암","보문",3);
			insert("보문","창신",2);
			insert("창신","동묘앞",2);
			insert("동묘앞","신당",2);
			insert("신당","청구",2);
			insert("청구","약수",2);
			insert("약수","버티고개",2);
			insert("버티고개","한강진",2);
			insert("한강진","이태원",2);
			insert("이태원","녹사평",2);
			insert("녹사평","삼각지",3);
			insert("삼각지","효참공원앞",2);
			insert("효참공원앞","공덕",2);
			insert("공덕","대흥",4);
			insert("대흥","광흥참",3);
			insert("광흥참","상수",2);
			insert("상수","합정",2);
			insert("합정","망원",2);
			insert("망원","마포구청",2);
			insert("마포구청","월드컵경기장",2);
			insert("월드컵경기장","디지털미디어시티",2);
			insert("디지털미디어시티","증산",2);
			insert("증산","새절",2);
			insert("새절","응암",2);
			insert("응암","역촌",2);
			insert("역촌","불광",2);
			insert("불광","독바위",2);
			insert("독바위","연신내",2);
			insert("연신내","구산",3);
			insert("구산","응암",2);
			//7호선
			insert("장암","도봉산",5);
			insert("도봉산","수락산",3);
			insert("수락산","마들",3);
			insert("마들","노원",2);
			insert("노원","중계",2);
			insert("중계","하계",2);
			insert("하계","공릉",2);
			insert("공릉","태릉입구",2);
			insert("태릉입구","먹골",2);
			insert("먹골","중화",2);
			insert("중화","상봉",3);
			insert("상봉","면목",2);
			insert("면목","사가정",2);
			insert("사가정","용마산",2);
			insert("용마산","중곡",2);
			insert("중곡","군자",2);
			insert("군자","어린이대공원",2);
			insert("어린이대공원","건대입구",2);
			insert("건대입구","뚝섬유원지",2);
			insert("뚝섬유원지","청담",3);
			insert("청담","강남구청",2);
			insert("강남구청","학동",2);
			insert("학동","논현",2);
			insert("논현","반포",2);
			insert("반포","고속터미널",2);
			insert("고속터미널","내방",3);
			insert("내방","총신대입구",3);
			insert("총신대입구","남성",2);
			insert("남성","숭실대입구",3);
			insert("숭실대입구","상도",2);
			insert("상도","장승배기",2);
			insert("장승배기","신대방삼거리",3);
			insert("신대방삼거리","보라매",2);
			insert("보라매","신풍",2);
			insert("신풍","대림",3);
			insert("대림","남구로",2);
			insert("남구로","가산디지털단지",2);
			insert("가산로디지털단지","철산",3);
			insert("철산","광명사거리",3);
			insert("광명사거리","천왕",3);
			insert("천왕","온수",3);
			insert("온수","까치울",3);
			insert("까치울","부천종합운동장",2);
			insert("부천종합운동장","춘의",2);
			insert("춘의","신중동",2);
			insert("신중동","부천시청",2);
			insert("부천시청","상동",2);
			insert("상동","삼산체육관",2);
			insert("삼산체육관","굴포천",2);
			insert("굴포천","부평구청",2);
			//8호선
			insert("암사","천호",2);
			insert("천호","강동구청",2);
			insert("강동구청","몽촌토성",3);
			insert("몽촌토성","잠실",2);
			insert("잠실","석촌",3);
			insert("석촌","송파",2);
			insert("송파","가락시장",2);
			insert("가락시장","문정",2);
			insert("문정","장지",3);
			insert("장지","복정",2);
			insert("복정","산성",4);
			insert("산성","남한산성입구",3);
			insert("남한산성입구","단대오거리",2);
			insert("단대오거리","신흥",2);
			insert("신흥","수진",2);
			insert("수진","모란",2);
			//9호선
			insert("개화","김포공항",6);
			insert("김포공항","공항시장",2);
			insert("공항시장","신방화",2);
			insert("신방화","마곡나루",3);
			insert("마곡나루","양천향교",3);
			insert("양천향교","가양",5);
			insert("가양","증미",2);
			insert("증미","등촌",2);
			insert("등촌","염창",3);
			insert("염창","신목동",3);
			insert("신목동","선유도",3);
			insert("선유도","당산",3);
			insert("당산","국회의사당",3);
			insert("국회의사당","여의도",2);
			insert("여의도","샛강",2);
			insert("샛강","노량진",3);
			insert("노량진","노들",2);
			insert("노들","흑석",3);
			insert("흑석","동작",3);
			insert("동작","구반포",3);
			insert("구반포","신반포",2);
			insert("신반포","고속터미널",3);
			insert("고속터미널","사평",2);
			insert("사평","신논현",3);
			insert("신논현","언주",2);
			insert("언주","선정릉",2);
			insert("선정릉","삼성중앙",3);
			insert("삼성중앙","봉은사",2);
			insert("봉은사","종합운동장",4);
			//분당선(10)
			insert("왕십리","서울숲",3);
			insert("서울숲","압구정로데오",2);
			insert("압구정로데오","강남구청",2);
			insert("강남구청","선정릉",2);
			insert("선정릉","선릉",2);
			insert("선릉","한티",2);
			insert("한티","도곡",2);
			insert("도곡","구룡",2);
			insert("구룡","개포동",2);
			insert("개포동","대모산입구",2);
			insert("대모산입구","수서",4);
			insert("수서","복정",3);
			insert("복정","가천대",2);
			insert("가천대","태평",2);
			insert("태평","모란",2);
			insert("모란","야탑",3);
			insert("야탑","이매",3);
			insert("이매","서현",2);
			insert("서현","수내",2);
			insert("수내","정자",3);
			insert("정자","미금",3);
			insert("미금","오리",2);
			insert("오리","죽전",4);
			insert("죽전","보정",2);
			insert("보정","구성",2);
			insert("구성","신갈",3);
			insert("신갈","기흥",3);
			insert("기흥","상갈",3);
			insert("상갈","청명",2);
			insert("청명","영통",3);
			insert("영통","망포",3);
			insert("망포","매탄권선",2);
			insert("매탄권선","수원시청",3);
			insert("수원시청","매교",3);
			insert("매교","수원",2);
			//인천선(11)
			insert("계양","귤현",3);
			insert("귤현","박촌",3);
			insert("박촌","임학",2);
			insert("임학","계산",2);
			insert("계산","경인교대입구",3);
			insert("경인교대입구","작전",2);
			insert("작전","갈산",2);
			insert("갈산","부평구청",2);
			insert("부평구청","부평시장",2);
			insert("부평시장","부평",2);
			insert("부평","동수",2);
			insert("동수","부평삼거리",2);
			insert("부평삼거리","간석오거리",2);
			insert("간석오거리","인천시청",3);
			insert("인천시청","예술회관",2);
			insert("인천터미널","문학경기장",2);
			insert("문학경기장","선학",2);
			insert("선학","신연수",2);
			insert("신연수","원인재",2);
			insert("원인재","동춘",2);
			insert("동춘","동막",2);
			insert("동막","캠퍼스타운",3);
			insert("캠퍼스타운","테크노파크",2);
			insert("테크노파크","지식정보단지",3);
			insert("지식정보단지","인천대입구",2);
			insert("인천대입구","센트럴파크",2);
			insert("센트럴파크","국제업무지구",1);
			//신분당선(12)
			insert("강남","양재",2);
			insert("양재","양재시민의숲",3);
			insert("양재시민의숲","청계산입구",3);
			insert("청계산입구","판교",7);
			insert("판교","정자",4);
			//경의중앙선(13)
			insert("문산","파주",3);
			insert("파주","월롱",3);
			insert("월롱","금촌",4);
			insert("금촌","금릉",3);
			insert("금릉","운정",4);
			insert("운정","야당",2);
			insert("야당","탄현",3);
			insert("탄현","일산",3);
			insert("일산","풍산",3);
			insert("풍산","백마",3);
			insert("백마","곡산",3);
			insert("곡산","대곡",3);
			insert("대곡","능곡",3);
			insert("능곡","행신",3);
			insert("행신","강매",2);
			insert("강매","화전",3);
			insert("화전","수색",4);
			insert("수색","디지털미디어시티",2);
			insert("디지털미디어시티","가좌",3);
			insert("가좌","신촌",4);
			insert("신촌","서울역",6);
			
			insert("가좌","홍대입구",3);
			insert("홍대입구","서강대",2);
			insert("서강대","공덕",3);
			insert("공덕","용산",5);
			insert("용산","이촌",4);
			insert("이촌","서빙고",3);
			insert("서빙고","한남",3);
			insert("한남","옥수",3);
			insert("옥수","응봉",3);
			insert("응봉","왕십리",2);
			insert("왕십리","청량리",4);
			insert("청량리","회기",2);
			insert("회기","중량",2);
			insert("중량","상봉",3);
			insert("상봉","망우",2);
			insert("망우","양원",3);
			insert("양원","구리",4);
			insert("구리","도농",3);
			insert("도농","양정",4);
			insert("양정","덕소",4);
			insert("덕소","도심",2);
			insert("도심","팔당",3);
			insert("팔당","운길산",5);
			insert("운길산","양수",5);
			insert("양수","신원",4);
			insert("신원","국수",4);
			insert("국수","아신",5);
			insert("아신","오빈",4);
			insert("오빈","양평",3);
			insert("양평","원덕",5);
			insert("원덕","용문",5);
			//경춘선(14)
			insert("춘천","남춘천",4);
			insert("남춘천","김유정",6);
			insert("김유정","강촌",6);
			insert("강촌","백양리",5);
			insert("백양리","굴봉산",4);
			insert("굴봉산","가평",5);
			insert("가평","상천",5);
			insert("상천","청평",6);
			insert("청평","대성리",5);
			insert("대성리","마석",6);
			insert("마석","천마산",4);
			insert("천마산","평내호평",2);
			insert("평내호평","금곡",4);
			insert("금곡","사릉",4);
			insert("사릉","퇴계원",4);
			insert("퇴계원","별내",3);
			insert("별내","갈매",2);
			insert("갈매","신내",3);
			insert("신내","망우",3);
			insert("망우","상봉",3);
			//공항선(15)
			insert("인천국제공항","공항화물청사",4);
			insert("공항화물청사","운서",4);
			insert("운서","청라국제도시",11);
			insert("청라국제도시","검암",4);
			insert("검암","계양",6);
			insert("계양","김포공항",6);
			insert("김포공항","디지털미디어시티",9);
			insert("디지털미디어시티","홍대입구",3);
			insert("홍대입구","공덕",3);
			insert("공덕","서울역",4);
			//의정부선(16)
			insert("발곡","회룡",2);
			insert("회룡","범골",2);
			insert("범골","경전철의정부",2);
			insert("경전철의정부","의정부시청",2);
			insert("의정부시청","흥선",2);
			insert("흥선","의정부중앙",2);
			insert("의정부중앙","동오",2);
			insert("동오","새말",2);
			insert("새말","경기도청북부청사",1);
			insert("경기도청북부청사","효자",1);
			insert("효자","곤제",1);
			insert("곤제","어룡",2);
			insert("어룡","송산",2);
			insert("송산","탑석",1);
			//수인선(17)
			insert("송도","연수",4);
			insert("연수","원인재",2);
			insert("원인재","남동인더스파크",2);
			insert("남동인더스파크","호구포",2);
			insert("호구포","인천논현",2);
			insert("인천논현","소래포구",2);
			insert("소래포구","월곶",2);
			insert("월곶","달월",2);
			insert("달월","오이도",3);
			//에버라인선(18)
			
			
			
	}
	
	static void insert(String name1, String name2, int time) {	//노선을 구성하기 위한 함수 역1, 역2, 걸리는 시간
		int Index1, Index2;
		Index1 = searchStation(name1);
		Index2 = searchStation(name2);

		// 두 역에 Edge를 추가.
		insertNode(Index1, Index2, time);
		insertNode(Index2, Index1, time);	
		
		
	}
	
	// 역 이름을 갖고 역 번호를 찾는 함수.
	// 해당 역이 S[] 에 존재하지 않으면 마지막에 추가하고 그 번호를 반환.
	static int searchStation(String stationName) {
		int i=1;
		for (i = 1; i < stationCount; i++) { 
			if(S[i].sName.equals(stationName)){    //있으면
				return i;
			}		
		}
		// 없으면		
		S[i].sName = stationName;
		S[i].link = null;
		S[i].visited = false;
		stationCount++;
		
		return i;
	}

	// SubNode 에 Node 를 삽입하는 함수.
	// 삽입되는 노드의 가중치는 시간 dis(분)
	static void insertNode(int subNodeNum, int nodeNum, int dis) {
		// 메모리에 NodeType 구조체를 생성하고 그 주소를 newNode 에 치환
		NodeType newNode = new NodeType();			// 새로 추가될 노드의 값을 입력
		newNode.nodeNum = nodeNum;
		newNode.dis = dis;		// 노드를 맨 앞에 삽입
		newNode.next = S[subNodeNum].link;
		S[subNodeNum].link = newNode;
		
	}

	

	static int Dijkstra(int startNodeNum, int endNodeNum) {		
	
		// 경로를 출력하기 위해 트리를 역탐색 하게 되므로 처음부터 endNode 를 기준으로 최단 경로 트리를 구성한다.

		// 최단거리 트리를 구성하기 위해 pNode와 dist, next 값을 초기화
		int i;
		for (i = 0; i < stationCount; i++) {
			S[i].pNode = 0;
			S[i].dist = 30000;	// 무한대
			S[i].next = 0;
		}

		// 종착 노드의 최단거리는 0
		S[endNodeNum].dist = 0;		//경로를 출력하기 위해서 끝노드 부터
		// 최단거리가 예상되는 그룹 V2 의 링크드 리스트 초기값 (종착 노드)
		int V2 = endNodeNum;
		
		// V2 그룹이 사라질때까지
		while (V2 > 0) {
			// V2 에서 dist 가 최소인 노드를 찾는다.
			//초기화
			int prevNodeNum = 0;
			int nodeNum = V2;
			int minPrevNodeNum = 0;
			int minNodeNum = V2;
			int minDist = 30000;
			

			while (nodeNum > 0) {		//노드넘버가 있으면 
				if (S[nodeNum].dist < minDist) {	//현재 노드의 거리가 저장된 최소거리값보다 작을때
					minDist = S[nodeNum].dist;
					minNodeNum = nodeNum;
					minPrevNodeNum = prevNodeNum;
				}
				prevNodeNum = nodeNum;		//다음 노드로
				nodeNum = S[nodeNum].next;
				
			}

	   
			// 인접 노드들중 V2 에 속하지 않은 것은 추가하고
			// Dist 값들을 갱신해준다.
			NodeType newNode = S[minNodeNum].link;

			while (newNode != null) {			
				if (S[newNode.nodeNum].dist == 30000) {		// 인접 노드의 Dist 가 무한대라면(V2 에 속하지 않았다면)
					// V2 에 추가
					S[newNode.nodeNum].next = V2;
					V2 = newNode.nodeNum;
				}

				// Min 노드의 Dist 와 인접 노드의 dis 합이 인접 노드의 Dist 보다 작다면 치환.
				// 즉, Min 노드를 통해 인접 노드에 도달하는 거리가 더 가깝다면.
				if (S[newNode.nodeNum].dist > S[minNodeNum].dist + newNode.dis) {
					S[newNode.nodeNum].dist = S[minNodeNum].dist + newNode.dis;
					// 부모 노드를 Min 노드로 설정
					S[newNode.nodeNum].pNode = minNodeNum;
				}
				newNode = newNode.next;
			}
			
			// Min 노드를 V2 그룹에서 제거
			// 단, 첫번째 노드라면 특별히 제거
			if (V2 == minNodeNum) {
				V2 = S[minNodeNum].next;			
			}	else {			
				S[minPrevNodeNum].next = S[minNodeNum].next;
			}		
		}
		
		// StartNode 로 부터 EndNode 로 역탐색
		int nodeNum = startNodeNum;
		int j=0;
		// 거리가 무한대라면 탐색하지 않음
		if (S[startNodeNum].dist == 30000) {
			System.out.print( S[startNodeNum].sName + "에서 " + S[endNodeNum].sName + "까지의 경로는 존재하지 않음" );
		} 
		else {
			// 역탐색하여 경로 출력
			while (nodeNum != endNodeNum) {
				System.out.print( S[nodeNum].sName + " -> ");
				V[j].sName	= 	S[nodeNum].sName;
				nodeNum = S[nodeNum].pNode;
				
				s_count++;
				j++;
			}
			// System.out.println( S[nodeNum].sName );
			V[j].sName	= 	S[nodeNum].sName;
			V[j].dist	= 	30000;
			System.out.print( "걸리는 시간: " + (S[startNodeNum].dist ) + "분 " );
		}
		stationCount = 1;// 역 수 초기화
		return S[startNodeNum].dist;
	}
}