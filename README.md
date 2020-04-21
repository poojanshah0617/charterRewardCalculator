# charterRewardCalculator

This the project to calculate the reward point of the customer for the transactions done in last 3 months.

Test case is provided to check the working of the api.

To test by passing your own input run the project and from postman hit the localhost:8080/api/getReward with the request body as

``{
	"customerName":"abcd",
	"customerAddress":"abcdefghi",
	"firstMonthTransactions":[100,102,103],
	"secondMonthTransactions":[50,55,56],
	"thirdMonthTransactions":[]
}``

it consists of the the list of transactions as per the month.

the response will consists of the rewards as per the monthly transaction as well as the total of all the 3 months.

``{
    "customerName": "abcd",
    "customerAddress": "abcdefghi",
    "firstMonthReward": 160,
    "secondMonthReward": 11,
    "thirdMonthReward": 0,
   "totalReward": 171
}``

Rewards calculation is as follow

2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction.
eg. $120 purchase= 2*20+1*50= 90 points



04/21/2020

Added one more api

#/api/getReward

Http.GET call 

Which is a get call, it will return the details of the customer and there reward details. For this customer and transactions are present in H2 database.

Response will be 
'''
[
    {
        "customerName": "BOB",
        "reward": [
            {
                "month": "January",
                "reward": 150,
                "transactionsList": [
                    150
                ]
            },
            {
                "month": "February",
                "reward": 52,
                "transactionsList": [
                    101
                ]
            },
            {
                "month": "March",
                "reward": 49,
                "transactionsList": [
                    99,
                    49
                ]
            }
        ],
        "totalReward": 251
    },
    {
        "customerName": "MATT",
        "reward": [
            {
                "month": "January",
                "reward": 1,
                "transactionsList": [
                    51
                ]
            },
            {
                "month": "February",
                "reward": 1,
                "transactionsList": [
                    51
                ]
            },
            {
                "month": "March",
                "reward": 0,
                "transactionsList": [
                    40
                ]
            }
        ],
        "totalReward": 2
    },
    {
        "customerName": "ROY",
        "reward": [
            {
                "month": "January",
                "reward": 50,
                "transactionsList": [
                    100
                ]
            },
            {
                "month": "February",
                "reward": 0,
                "transactionsList": [
                    50
                ]
            },
            {
                "month": "March",
                "reward": 96,
                "transactionsList": [
                    123
                ]
            }
        ],
        "totalReward": 146
    }
]
'''
