import java.util.Scanner;
// ������ ���̾ƿ��� �ٸ� ȣ��
public class Subway {
	public static void main(String [] args) {
        Scanner scan1 = new Scanner(System.in);      // ���� �Է��� ���ڷ� Scanner ����
		System.out.println("��߿� : ");
		String start = scan1.nextLine();
		
		Scanner scan2 = new Scanner(System.in);
		System.out.println("������ : ");
		String end	= scan2.nextLine();
		
		Search di = new Search();	//�ִܳ뼱 �˻� ��ü ����
		di.s_count=0;				//��ó�� ������ �� �ʱ�ȭ
		
		int time = di.Dijkstra(di.searchStation(start), di.searchStation(end));//���ͽ�Ʈ�� �˰������� �� Ž��
		int i,j=0;
		if(time==0 || time==30000) {
			System.out.println("\n\n\n      �߸��Է��Ͽ����ϴ�!!\n\n      ���� ����� �Է��Ͻʽÿ�.");
		}
		else {
			System.out.println("\n    <�ּҽð�>\n\n");
			System.out.println("  �ҿ�ð� :  �� " +  time + "��\n\n");
			System.out.println("  ������� : " +   di.s_count     + "\n\n");
			System.out.println("  ��        ��  \n\n"); 
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
	public static final int MAX_STATIONS	= 400; // ����� ����ö �뼱�� �� ���� �� 400��
	public static int stationCount = 1;// �� ��
	public static SubType[] S = new SubType[MAX_STATIONS];	// ����ö ���� ����Ʈ
	public static SubType[] V = new SubType[MAX_STATIONS];	// ����� ����
	public static int s_count=0;//��ó�� ������ ��

	public Search() {
			for(int i=0; i < MAX_STATIONS; i++) {
				S[i] = new SubType();
				V[i] = new SubType();

			}
			//1ȣ��
			insert("�ҿ��","����õ",4);
			insert("����õ","����",2);
			insert("����","����õ�߾�",2);
			insert("����õ�߾�","����",2);
			insert("����","����",5);
			insert("����","����",6);
			insert("����","����",3);
			insert("����","���",3);
			insert("���","����",2);
			insert("����","������",3);
			insert("������","ȸ��",3);
			insert("ȸ��","������",3);
			insert("������","������",3);
			insert("������","����",2);
			insert("����","����",2);
			insert("����","â��",2);
			insert("â��","��õ",2);
			insert("��õ","����",3);
			insert("����","�����",3);
			insert("�����","����",2);
			insert("����","���̹�",2);
			insert("���̹�","�ܴ��",1);
			insert("�ܴ��","ȸ��",2);
			insert("ȸ��","û����",3);
			insert("û����","���⵿",2);
			insert("���⵿","�ż���",2);
			insert("�ż���","������",2);
			insert("������","���빮",2);
			insert("���빮","����5��",2);
			insert("����5��","����3��",2);
			insert("����3��","����",1);
			insert("����","��û",3);
			insert("��û","���￪",2);
			insert("���￪","����",3);
			insert("����","���",2);
			insert("���","�뷮��",3);
			insert("�뷮��","���",2);
			insert("���","�ű�",2);
			insert("�ű�","������",2);
			insert("������","�ŵ���",3);
			insert("�ŵ���","����",1);
			insert("����","����",2);
			insert("����","����",3);
			insert("����","������",2);
			insert("������","�¼�",3);
			insert("�¼�","����",2);
			insert("����","�һ�",3);
			insert("�һ�","��õ",2);
			insert("��õ","�ߵ�",2);
			insert("�ߵ�","�۳�",2);
			insert("�۳�","�ΰ�",3);
			insert("�ΰ�","����",2);
			insert("����","���",3);
			insert("���","����",2);
			insert("����","����",3);
			insert("����","�־�",2);
			insert("�־�","��ȭ",1);
			insert("��ȭ","������",1);
			insert("������","����",2);
			insert("����","����õ",2);
			insert("����õ","��õ",4);
			
			insert("����","��������д���",5);
			insert("��������д���","����",4);
			insert("����","��õ��û",3);
			insert("��õ��û","����",5);
			
			insert("��õ��û","����",3);
			insert("����","����",2);
			insert("����","�Ⱦ�",3);
			insert("�Ⱦ�","����",3);
			insert("����","����",3);
			insert("����","����",3);
			insert("����","����",1);
			insert("����","�ǿ�",2);
			insert("�ǿ�","���հ���",4);
			insert("���հ���","ȭ��",3);
			insert("ȭ��","����",4);
			insert("����","����",4);
			insert("����","����",4);
			insert("����","����ź",3);
			
			insert("����","����",4);
			insert("����","�����",4);
			insert("�����","����",3);
			insert("����","����",4);
			insert("����","��ź",4);
			insert("��ź","������",3);
			insert("������","����",4);
			insert("����","����",4);
			insert("����","��ȯ",7);
			insert("��ȯ","����",5);
			insert("����","����",4);
			insert("����","õ��",3);
			insert("õ��","����",2);
			insert("����","�ֿ�",2);
			insert("�ֿ�","�ƻ�",2);
			insert("�ƻ�","���",4);
			insert("���","�¾��õ",4);
			insert("�¾��õ","��â",4);
			//2ȣ��
			insert("�ż���","���",2);
			insert("���","�Ŵ�",2);
			insert("�Ŵ�","���",2);
			insert("���","����",3);
			insert("����","�Ǵ��Ա�",2);
			insert("�Ǵ��Ա�","����",3);
			insert("����","����",1);
			insert("����","��ǳ���",2);
			insert("��ǳ���","���",3);
			insert("���","��õ",2);
			insert("��õ","���տ��",2);
			insert("���տ��","�Ｚ",2);
			insert("�Ｚ","����",2);
			insert("����","����",2);
			insert("����","����",2);
			insert("����","����",2);
			insert("����","����",2);
			insert("����","���",2);
			insert("���","���",2);
			insert("���","������",2);
			insert("������","������Ա�",3);
			insert("������Ա�","��õ",2);
			insert("��õ","�Ÿ�",2);
			insert("�Ÿ�","�Ŵ��",2);
			insert("�Ŵ��","���ε����д���",2);
			insert("���ε����д���","�븲",2);
			insert("�븲","�ŵ���",3);
			
			insert("�ŵ���","����õ",2);
			insert("����õ","��õ��û",3);
			insert("��õ��û","�����װŸ�",4);
			insert("�����װŸ�","��ġ��",3);
			
			insert("�ŵ���","����",2);
			insert("����","��������û",2);
			insert("��������û","���",2);
			insert("���","����",4);
			insert("����","ȫ���Ա�",2);
			insert("ȫ���Ա�","����",2);
			insert("����","�̴�",2);
			insert("�̴�","����",2);
			insert("����","������",2);
			insert("������","��û",3);
			insert("��û","�������Ա�",2);
			insert("�������Ա�","������3��",2);
			insert("������3��","������4��",1);
			insert("������4��","���빮���繮ȭ����",2);
			insert("���빮���繮ȭ����","�Ŵ�",2);
			insert("�Ŵ�","��սʸ�",2);
			insert("��սʸ�","�սʸ�",2);
			insert("�սʸ�","�Ѿ��",2);
			insert("�Ѿ��","�Ҽ�",2);
			insert("�Ҽ�","����",1);
			//3ȣ��
			insert("��ȭ","�ֿ�",2);
			insert("�ֿ�","���߻�",3);
			insert("���߻�","����",2);
			insert("����","�鼮",2);
			insert("�鼮","���",4);
			insert("���","ȭ��",3);
			insert("ȭ��","����",3);
			insert("����","����",3);
			insert("����","���",3);
			insert("���","����",3);
			insert("����","���",3);
			insert("���","����",3);
			insert("����","���Ĺ�",4);
			insert("���Ĺ�","���ų�",2);
			insert("���ų�","�ұ�",2);
			insert("�ұ�","���",2);
			insert("���","ȫ��",3);
			insert("ȫ��","������",2);
			insert("������","������",2);
			insert("������","�溹��",2);
			insert("�溹��","�ȱ�",2);
			insert("�ȱ�","����3��",2);
			insert("����3��","������3��",2);
			insert("������3��","�湫��",1);
			insert("�湫��","�����Ա�",3);
			insert("�����Ա�","���",3);
			insert("���","��ȣ",2);
			insert("��ȣ","����",1);
			insert("����","�б���",2);
			insert("�б���","�Ż�",2);
			insert("�Ż�","���",3);
			insert("���","����͹̳�",2);
			insert("����͹̳�","����",2);
			insert("����","�����͹̳�",2);
			insert("�����͹̳�","����",3);
			insert("����","�ź�",2);
			insert("�ź�","����",2);
			insert("����","��ġ",2);
			insert("��ġ","�п���",1);
			insert("�п���","��û",2);
			insert("��û","�Ͽ�",2);
			insert("�Ͽ�","����",3);
			insert("����","��������",3);
			insert("��������","��������",2);
			insert("��������","����",1);
			//4ȣ��
			insert("���","���",2);
			insert("���","���",2);
			insert("���","â��",2);
			insert("â��","�ֹ�",2);
			insert("�ֹ�","����",3);
			insert("����","�̾�",2);
			insert("�̾�","�̾ƻ�Ÿ�",2);
			insert("�̾ƻ�Ÿ�","����",2);
			insert("����","���ſ����Ա�",3);
			insert("���ſ����Ա�","�Ѽ����Ա�",2);
			insert("�Ѽ����Ա�","��ȭ",2);
			insert("��ȭ","���빮",2);
			insert("���빮","���빮���繮ȭ����",2);
			insert("���빮���繮ȭ����","�湫��",2);
			insert("�湫��","��",1);
			insert("��","ȸ��",2);
			insert("ȸ��","���￪",2);
			insert("���￪","�����Ա�",2);
			insert("�����Ա�","�ﰢ��",2);
			insert("�ﰢ��","�ſ��",1);
			insert("�ſ��","����",2);
			insert("����","����",4);
			insert("����","�ѽŴ��Ա�",3);
			insert("�ѽŴ��Ա�","���",2);
			insert("���","���·�",1);
			insert("���·�","������",3);
			insert("������","�渶����",3);
			insert("�渶����","�����",2);
			insert("�����","��õ",2);
			insert("��õ","���ΰ�õ÷��",3);
			insert("���ΰ�õ÷��","�δ���",2);
			insert("�δ���","����",2);
			insert("����","����",2);
			insert("����","����",3);
			insert("����","�꺻",4);
			insert("�꺻","������",2);
			insert("������","��߹�",3);
			insert("��߹�","�ݿ�",3);
			insert("�ݿ�","��ϼ�",4);
			insert("��ϼ�","�Ѵ��",2);
			insert("�Ѵ��","�߾�",2);
			insert("�߾�","����",2);
			insert("����","����",3);
			insert("����","�Ȼ�",4);
			insert("�Ȼ�","���õ",2);
			insert("���õ","���̵�",2);
			//5ȣ��
			insert("��ȭ","��ȭ��",2);
			insert("��ȭ��","��������",3);
			insert("��������","����",3);
			insert("����","����",2);
			insert("����","�߻�",3);
			insert("�߻�","�����",2);
			insert("�����","ȭ��",2);
			insert("ȭ��","��ġ��",2);
			insert("��ġ��","����",3);
			insert("����","��",2);
			insert("��","����",3);
			insert("����","����",2);
			insert("����","��������û",2);
			insert("��������û","����������",2);
			insert("����������","�ű�",2);
			insert("�ű�","���ǵ�",2);
			insert("���ǵ�","���ǳ���",2);
			insert("���ǳ���","����",3);
			insert("����","����",3);
			insert("����","�ֿ���",2);
			insert("�ֿ���","������",2);
			insert("������","���빮",2);
			insert("���빮","��ȭ��",2);
			insert("��ȭ��","����3��",2);
			insert("����3��","������4��",1);
			insert("������4��","���빮���繮ȭ����",2);
			insert("���빮���繮ȭ����","û��",2);
			insert("û��","�ű�ȣ",2);
			insert("�ű�ȣ","���",2);
			insert("���","�սʸ�",2);
			insert("�սʸ�","����",2);
			insert("����","��ʸ�",2);
			insert("��ʸ�","������",3);
			insert("������","����",2);
			insert("����","��ġ��",3);
			insert("��ġ��","������",3);
			insert("������","õȣ",3);
			insert("õȣ","����",2);
			insert("����","�浿",2);
			insert("�浿","�����ٸ�",2);
			insert("�����ٸ�","����",2);
			insert("����","���",3);
			insert("���","���ϵ�",3);
			
			insert("����","���̵�",3);
			insert("���̵�","�ø��Ȱ���",3);
			insert("�ø��Ȱ���","����",2);
			insert("����","����",2);
			insert("����","����",2);
			insert("����","�ſ�",2);
			insert("�ſ�","��õ",2);
			//6ȣ��
			insert("��ȭ��","ȭ����",2);
			insert("ȭ����","�¸��Ա�",2);
			insert("�¸��Ա�","����",2);
			insert("����","������",2);
			insert("������","�����",2);
			insert("�����","����",2);
			insert("����","�����",3);
			insert("�����","�Ⱦ�",3);
			insert("�Ⱦ�","����",3);
			insert("����","â��",2);
			insert("â��","������",2);
			insert("������","�Ŵ�",2);
			insert("�Ŵ�","û��",2);
			insert("û��","���",2);
			insert("���","��Ƽ��",2);
			insert("��Ƽ��","�Ѱ���",2);
			insert("�Ѱ���","���¿�",2);
			insert("���¿�","�����",2);
			insert("�����","�ﰢ��",3);
			insert("�ﰢ��","ȿ��������",2);
			insert("ȿ��������","����",2);
			insert("����","����",4);
			insert("����","������",3);
			insert("������","���",2);
			insert("���","����",2);
			insert("����","����",2);
			insert("����","������û",2);
			insert("������û","�����Ű����",2);
			insert("�����Ű����","�����й̵���Ƽ",2);
			insert("�����й̵���Ƽ","����",2);
			insert("����","����",2);
			insert("����","����",2);
			insert("����","����",2);
			insert("����","�ұ�",2);
			insert("�ұ�","������",2);
			insert("������","���ų�",2);
			insert("���ų�","����",3);
			insert("����","����",2);
			//7ȣ��
			insert("���","������",5);
			insert("������","������",3);
			insert("������","����",3);
			insert("����","���",2);
			insert("���","�߰�",2);
			insert("�߰�","�ϰ�",2);
			insert("�ϰ�","����",2);
			insert("����","�¸��Ա�",2);
			insert("�¸��Ա�","�԰�",2);
			insert("�԰�","��ȭ",2);
			insert("��ȭ","���",3);
			insert("���","���",2);
			insert("���","�簡��",2);
			insert("�簡��","�븶��",2);
			insert("�븶��","�߰�",2);
			insert("�߰�","����",2);
			insert("����","��̴����",2);
			insert("��̴����","�Ǵ��Ա�",2);
			insert("�Ǵ��Ա�","�Ҽ�������",2);
			insert("�Ҽ�������","û��",3);
			insert("û��","������û",2);
			insert("������û","�е�",2);
			insert("�е�","����",2);
			insert("����","����",2);
			insert("����","����͹̳�",2);
			insert("����͹̳�","����",3);
			insert("����","�ѽŴ��Ա�",3);
			insert("�ѽŴ��Ա�","����",2);
			insert("����","���Ǵ��Ա�",3);
			insert("���Ǵ��Ա�","��",2);
			insert("��","��¹��",2);
			insert("��¹��","�Ŵ���Ÿ�",3);
			insert("�Ŵ���Ÿ�","�����",2);
			insert("�����","��ǳ",2);
			insert("��ǳ","�븲",3);
			insert("�븲","������",2);
			insert("������","��������д���",2);
			insert("����ε����д���","ö��",3);
			insert("ö��","�����Ÿ�",3);
			insert("�����Ÿ�","õ��",3);
			insert("õ��","�¼�",3);
			insert("�¼�","��ġ��",3);
			insert("��ġ��","��õ���տ��",2);
			insert("��õ���տ��","����",2);
			insert("����","���ߵ�",2);
			insert("���ߵ�","��õ��û",2);
			insert("��õ��û","��",2);
			insert("��","���ü����",2);
			insert("���ü����","����õ",2);
			insert("����õ","����û",2);
			//8ȣ��
			insert("�ϻ�","õȣ",2);
			insert("õȣ","������û",2);
			insert("������û","�����伺",3);
			insert("�����伺","���",2);
			insert("���","����",3);
			insert("����","����",2);
			insert("����","��������",2);
			insert("��������","����",2);
			insert("����","����",3);
			insert("����","����",2);
			insert("����","�꼺",4);
			insert("�꼺","���ѻ꼺�Ա�",3);
			insert("���ѻ꼺�Ա�","�ܴ���Ÿ�",2);
			insert("�ܴ���Ÿ�","����",2);
			insert("����","����",2);
			insert("����","���",2);
			//9ȣ��
			insert("��ȭ","��������",6);
			insert("��������","���׽���",2);
			insert("���׽���","�Ź�ȭ",2);
			insert("�Ź�ȭ","�����",3);
			insert("�����","��õ�ⱳ",3);
			insert("��õ�ⱳ","����",5);
			insert("����","����",2);
			insert("����","����",2);
			insert("����","��â",3);
			insert("��â","�Ÿ�",3);
			insert("�Ÿ�","������",3);
			insert("������","���",3);
			insert("���","��ȸ�ǻ��",3);
			insert("��ȸ�ǻ��","���ǵ�",2);
			insert("���ǵ�","����",2);
			insert("����","�뷮��",3);
			insert("�뷮��","���",2);
			insert("���","�漮",3);
			insert("�漮","����",3);
			insert("����","������",3);
			insert("������","�Ź���",2);
			insert("�Ź���","����͹̳�",3);
			insert("����͹̳�","����",2);
			insert("����","�ų���",3);
			insert("�ų���","����",2);
			insert("����","������",2);
			insert("������","�Ｚ�߾�",3);
			insert("�Ｚ�߾�","������",2);
			insert("������","���տ��",4);
			//�д缱(10)
			insert("�սʸ�","���｣",3);
			insert("���｣","�б����ε���",2);
			insert("�б����ε���","������û",2);
			insert("������û","������",2);
			insert("������","����",2);
			insert("����","��Ƽ",2);
			insert("��Ƽ","����",2);
			insert("����","����",2);
			insert("����","������",2);
			insert("������","�����Ա�",2);
			insert("�����Ա�","����",4);
			insert("����","����",3);
			insert("����","��õ��",2);
			insert("��õ��","����",2);
			insert("����","���",2);
			insert("���","��ž",3);
			insert("��ž","�̸�",3);
			insert("�̸�","����",2);
			insert("����","����",2);
			insert("����","����",3);
			insert("����","�̱�",3);
			insert("�̱�","����",2);
			insert("����","����",4);
			insert("����","����",2);
			insert("����","����",2);
			insert("����","�Ű�",3);
			insert("�Ű�","����",3);
			insert("����","��",3);
			insert("��","û��",2);
			insert("û��","����",3);
			insert("����","����",3);
			insert("����","��ź�Ǽ�",2);
			insert("��ź�Ǽ�","������û",3);
			insert("������û","�ű�",3);
			insert("�ű�","����",2);
			//��õ��(11)
			insert("���","����",3);
			insert("����","����",3);
			insert("����","����",2);
			insert("����","���",2);
			insert("���","���α����Ա�",3);
			insert("���α����Ա�","����",2);
			insert("����","����",2);
			insert("����","����û",2);
			insert("����û","�������",2);
			insert("�������","����",2);
			insert("����","����",2);
			insert("����","�����Ÿ�",2);
			insert("�����Ÿ�","�������Ÿ�",2);
			insert("�������Ÿ�","��õ��û",3);
			insert("��õ��û","����ȸ��",2);
			insert("��õ�͹̳�","���а����",2);
			insert("���а����","����",2);
			insert("����","�ſ���",2);
			insert("�ſ���","������",2);
			insert("������","����",2);
			insert("����","����",2);
			insert("����","ķ�۽�Ÿ��",3);
			insert("ķ�۽�Ÿ��","��ũ����ũ",2);
			insert("��ũ����ũ","������������",3);
			insert("������������","��õ���Ա�",2);
			insert("��õ���Ա�","��Ʈ����ũ",2);
			insert("��Ʈ����ũ","������������",1);
			//�źд缱(12)
			insert("����","����",2);
			insert("����","����ù��ǽ�",3);
			insert("����ù��ǽ�","û����Ա�",3);
			insert("û����Ա�","�Ǳ�",7);
			insert("�Ǳ�","����",4);
			//�����߾Ӽ�(13)
			insert("����","����",3);
			insert("����","����",3);
			insert("����","����",4);
			insert("����","�ݸ�",3);
			insert("�ݸ�","����",4);
			insert("����","�ߴ�",2);
			insert("�ߴ�","ź��",3);
			insert("ź��","�ϻ�",3);
			insert("�ϻ�","ǳ��",3);
			insert("ǳ��","�鸶",3);
			insert("�鸶","���",3);
			insert("���","���",3);
			insert("���","�ɰ�",3);
			insert("�ɰ�","���",3);
			insert("���","����",2);
			insert("����","ȭ��",3);
			insert("ȭ��","����",4);
			insert("����","�����й̵���Ƽ",2);
			insert("�����й̵���Ƽ","����",3);
			insert("����","����",4);
			insert("����","���￪",6);
			
			insert("����","ȫ���Ա�",3);
			insert("ȫ���Ա�","������",2);
			insert("������","����",3);
			insert("����","���",5);
			insert("���","����",4);
			insert("����","������",3);
			insert("������","�ѳ�",3);
			insert("�ѳ�","����",3);
			insert("����","����",3);
			insert("����","�սʸ�",2);
			insert("�սʸ�","û����",4);
			insert("û����","ȸ��",2);
			insert("ȸ��","�߷�",2);
			insert("�߷�","���",3);
			insert("���","����",2);
			insert("����","���",3);
			insert("���","����",4);
			insert("����","����",3);
			insert("����","����",4);
			insert("����","����",4);
			insert("����","����",2);
			insert("����","�ȴ�",3);
			insert("�ȴ�","����",5);
			insert("����","���",5);
			insert("���","�ſ�",4);
			insert("�ſ�","����",4);
			insert("����","�ƽ�",5);
			insert("�ƽ�","����",4);
			insert("����","����",3);
			insert("����","����",5);
			insert("����","�빮",5);
			//���ἱ(14)
			insert("��õ","����õ",4);
			insert("����õ","������",6);
			insert("������","����",6);
			insert("����","��縮",5);
			insert("��縮","������",4);
			insert("������","����",5);
			insert("����","��õ",5);
			insert("��õ","û��",6);
			insert("û��","�뼺��",5);
			insert("�뼺��","����",6);
			insert("����","õ����",4);
			insert("õ����","��ȣ��",2);
			insert("��ȣ��","�ݰ�",4);
			insert("�ݰ�","�縪",4);
			insert("�縪","����",4);
			insert("����","����",3);
			insert("����","����",2);
			insert("����","�ų�",3);
			insert("�ų�","����",3);
			insert("����","���",3);
			//���׼�(15)
			insert("��õ��������","����ȭ��û��",4);
			insert("����ȭ��û��","�",4);
			insert("�","û��������",11);
			insert("û��������","�˾�",4);
			insert("�˾�","���",6);
			insert("���","��������",6);
			insert("��������","�����й̵���Ƽ",9);
			insert("�����й̵���Ƽ","ȫ���Ա�",3);
			insert("ȫ���Ա�","����",3);
			insert("����","���￪",4);
			//�����μ�(16)
			insert("�߰�","ȸ��",2);
			insert("ȸ��","����",2);
			insert("����","����ö������",2);
			insert("����ö������","�����ν�û",2);
			insert("�����ν�û","�Ｑ",2);
			insert("�Ｑ","�������߾�",2);
			insert("�������߾�","����",2);
			insert("����","����",2);
			insert("����","��⵵û�Ϻ�û��",1);
			insert("��⵵û�Ϻ�û��","ȿ��",1);
			insert("ȿ��","����",1);
			insert("����","���",2);
			insert("���","�ۻ�",2);
			insert("�ۻ�","ž��",1);
			//���μ�(17)
			insert("�۵�","����",4);
			insert("����","������",2);
			insert("������","�����δ�����ũ",2);
			insert("�����δ�����ũ","ȣ����",2);
			insert("ȣ����","��õ����",2);
			insert("��õ����","�ҷ�����",2);
			insert("�ҷ�����","����",2);
			insert("����","�޿�",2);
			insert("�޿�","���̵�",3);
			//�������μ�(18)
			
			
			
	}
	
	static void insert(String name1, String name2, int time) {	//�뼱�� �����ϱ� ���� �Լ� ��1, ��2, �ɸ��� �ð�
		int Index1, Index2;
		Index1 = searchStation(name1);
		Index2 = searchStation(name2);

		// �� ���� Edge�� �߰�.
		insertNode(Index1, Index2, time);
		insertNode(Index2, Index1, time);	
		
		
	}
	
	// �� �̸��� ���� �� ��ȣ�� ã�� �Լ�.
	// �ش� ���� S[] �� �������� ������ �������� �߰��ϰ� �� ��ȣ�� ��ȯ.
	static int searchStation(String stationName) {
		int i=1;
		for (i = 1; i < stationCount; i++) { 
			if(S[i].sName.equals(stationName)){    //������
				return i;
			}		
		}
		// ������		
		S[i].sName = stationName;
		S[i].link = null;
		S[i].visited = false;
		stationCount++;
		
		return i;
	}

	// SubNode �� Node �� �����ϴ� �Լ�.
	// ���ԵǴ� ����� ����ġ�� �ð� dis(��)
	static void insertNode(int subNodeNum, int nodeNum, int dis) {
		// �޸𸮿� NodeType ����ü�� �����ϰ� �� �ּҸ� newNode �� ġȯ
		NodeType newNode = new NodeType();			// ���� �߰��� ����� ���� �Է�
		newNode.nodeNum = nodeNum;
		newNode.dis = dis;		// ��带 �� �տ� ����
		newNode.next = S[subNodeNum].link;
		S[subNodeNum].link = newNode;
		
	}

	

	static int Dijkstra(int startNodeNum, int endNodeNum) {		
	
		// ��θ� ����ϱ� ���� Ʈ���� ��Ž�� �ϰ� �ǹǷ� ó������ endNode �� �������� �ִ� ��� Ʈ���� �����Ѵ�.

		// �ִܰŸ� Ʈ���� �����ϱ� ���� pNode�� dist, next ���� �ʱ�ȭ
		int i;
		for (i = 0; i < stationCount; i++) {
			S[i].pNode = 0;
			S[i].dist = 30000;	// ���Ѵ�
			S[i].next = 0;
		}

		// ���� ����� �ִܰŸ��� 0
		S[endNodeNum].dist = 0;		//��θ� ����ϱ� ���ؼ� ����� ����
		// �ִܰŸ��� ����Ǵ� �׷� V2 �� ��ũ�� ����Ʈ �ʱⰪ (���� ���)
		int V2 = endNodeNum;
		
		// V2 �׷��� �����������
		while (V2 > 0) {
			// V2 ���� dist �� �ּ��� ��带 ã�´�.
			//�ʱ�ȭ
			int prevNodeNum = 0;
			int nodeNum = V2;
			int minPrevNodeNum = 0;
			int minNodeNum = V2;
			int minDist = 30000;
			

			while (nodeNum > 0) {		//���ѹ��� ������ 
				if (S[nodeNum].dist < minDist) {	//���� ����� �Ÿ��� ����� �ּҰŸ������� ������
					minDist = S[nodeNum].dist;
					minNodeNum = nodeNum;
					minPrevNodeNum = prevNodeNum;
				}
				prevNodeNum = nodeNum;		//���� ����
				nodeNum = S[nodeNum].next;
				
			}

	   
			// ���� ������ V2 �� ������ ���� ���� �߰��ϰ�
			// Dist ������ �������ش�.
			NodeType newNode = S[minNodeNum].link;

			while (newNode != null) {			
				if (S[newNode.nodeNum].dist == 30000) {		// ���� ����� Dist �� ���Ѵ���(V2 �� ������ �ʾҴٸ�)
					// V2 �� �߰�
					S[newNode.nodeNum].next = V2;
					V2 = newNode.nodeNum;
				}

				// Min ����� Dist �� ���� ����� dis ���� ���� ����� Dist ���� �۴ٸ� ġȯ.
				// ��, Min ��带 ���� ���� ��忡 �����ϴ� �Ÿ��� �� �����ٸ�.
				if (S[newNode.nodeNum].dist > S[minNodeNum].dist + newNode.dis) {
					S[newNode.nodeNum].dist = S[minNodeNum].dist + newNode.dis;
					// �θ� ��带 Min ���� ����
					S[newNode.nodeNum].pNode = minNodeNum;
				}
				newNode = newNode.next;
			}
			
			// Min ��带 V2 �׷쿡�� ����
			// ��, ù��° ����� Ư���� ����
			if (V2 == minNodeNum) {
				V2 = S[minNodeNum].next;			
			}	else {			
				S[minPrevNodeNum].next = S[minNodeNum].next;
			}		
		}
		
		// StartNode �� ���� EndNode �� ��Ž��
		int nodeNum = startNodeNum;
		int j=0;
		// �Ÿ��� ���Ѵ��� Ž������ ����
		if (S[startNodeNum].dist == 30000) {
			System.out.print( S[startNodeNum].sName + "���� " + S[endNodeNum].sName + "������ ��δ� �������� ����" );
		} 
		else {
			// ��Ž���Ͽ� ��� ���
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
			System.out.print( "�ɸ��� �ð�: " + (S[startNodeNum].dist ) + "�� " );
		}
		stationCount = 1;// �� �� �ʱ�ȭ
		return S[startNodeNum].dist;
	}
}