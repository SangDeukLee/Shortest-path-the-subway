import java.util.Scanner;

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

			insert("대곡","진천",2);
			insert("진천","월배",2);
			insert("월배","상인",1);
			insert("상인","월촌",2);
			insert("월촌","송현",2);
			insert("송현","성당못",2);
			insert("성당못","대명",1);
			insert("대명","안지랑",2);
			insert("안지랑","현충로",2);
			insert("현충로","영대병원",1);
			insert("영대병원","교대",2);
			insert("교대","명덕",2);
			insert("명덕","반월당",1);
			
			insert("반월당","중앙로",2);
			insert("중앙로","대구역",1);
			insert("대구역","칠성시장",2);
			insert("칠성시장","신천",2);
			insert("신천","동대구역",2);
			insert("동대구역","큰고개",1);
			insert("큰고개","아양교",2);
			insert("아양교","동촌",2);
			insert("동촌","해안",2);
			insert("해안","방촌",2);
			insert("방촌","용계",2);
			insert("용계","율하",2);
			insert("율하","신기",2);
			insert("신기","반야월",1);
			insert("반야월","각산",2);
			insert("각산","안심",2);
			
			insert("문양","다사",2);
			insert("다사","대실",2);
			insert("대실","강창",2);
			insert("강창","계명대",2);
			insert("계명대","성서산단",2);
			insert("성서산단","이곡",2);
			insert("이곡","용산",2);
			insert("용산","죽전",2);
			insert("죽전","감삼",2);
			insert("감삼","두류",2);
			insert("두류","내당",2);
			insert("내당","반고개",2);
			insert("반고개","신남",2);
			insert("신남","반월당",2);
			
			insert("반월당","경대병원",2);
			insert("경대병원","대구은행",2);
			insert("대구은행","범어",2);
			insert("범어","수성구청",2);
			insert("수성구청","만촌",2);
			insert("만촌","담티",2);
			insert("담티","연호",2);
			insert("연호","대공원",2);
			insert("대공원","고산",2);
			insert("고산","신매",2);
			insert("신매","사월",2);
			insert("사월","정평",2);
			insert("정평","영남대",2);
			
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