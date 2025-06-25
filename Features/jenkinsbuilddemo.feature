Feature: Jenkins build demo
  @TC1
  Scenario Outline: Jenkins build demo
    Given Navigate to the application page <comment>

    Examples:
    |comment|
    |  commentOne  |
    |  commentTwo  |
#    |  commentThree  |

  Scenario Outline: Read Excel File
#    Given Read and print Excel data <TCID>

    Examples:
#      |TCID|