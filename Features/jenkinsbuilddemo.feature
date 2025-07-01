Feature: Jenkins build demo
  @TC1
  Scenario Outline: Jenkins build demo
    Given Navigate to the application page <comment>

    Examples:
    |comment|
    |  commentOne  |
    |  commentTwo  |
#    |  commentThree  |

  @javapractice
  Scenario Outline: Read Excel File
    Given Read and print Excel data <TCID>
    Given Read and print JSON data

    Examples:
    |TCID|
    | data |