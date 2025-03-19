#include <iostream>
#include <math.h>
using namespace std;

int abs(int num);

int main()
{
	int* testCase = (int*)malloc(sizeof(int));

	int T;
	cin >> T;

	int x1, x2, y1, y2, r1, r2;
	float eq;

	for (int i = 0; i < T; i++)
	{
		scanf("%d %d %d %d %d %d", &x1, &y1, &r1, &x2, &y2, &r2);
		
		eq = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

		if (x1 == x2 && y1 == y2 && r1 == r2)
		{
			testCase[i] = -1;
		}
		else if (eq == abs(r1 + r2) || eq == abs(r1 - r2))
		{
			testCase[i] = 1;
		}
		else if (eq > abs(r1 - r2) && eq < abs(r1 + r2))
		{
			testCase[i] = 2;
		}
		else
			testCase[i] = 0;
	}

	for (int i = 0; i < T; i++)
		cout << testCase[i] << "\n";
}

int abs(int num)
{
	if (num >= 0)
		return num;
	else
		return -num;
}