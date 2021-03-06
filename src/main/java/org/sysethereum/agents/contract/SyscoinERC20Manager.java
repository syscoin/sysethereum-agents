package org.sysethereum.agents.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.4.
 */
@SuppressWarnings("rawtypes")
public class SyscoinERC20Manager extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b50611c44806100206000396000f3fe6080604052600436106100a75760003560e01c80638b502b7a116100645780638b502b7a14610328578063a71d75ca14610380578063af56f158146103aa578063cf496b101461040f578063f7daeb8514610440578063fe2e97181461047c576100a7565b8063085e7092146100ac57806317c047e1146101535780631b728920146101a55780632f3489c7146101c85780635f959b69146101f85780636cde8d6f146102e9575b600080fd5b3480156100b857600080fd5b506100dc600480360360208110156100cf57600080fd5b503563ffffffff166104be565b60405180878152602001868152602001856001600160a01b03166001600160a01b03168152602001846001600160a01b03166001600160a01b031681526020018363ffffffff1663ffffffff16815260200182600481111561013a57fe5b60ff168152602001965050505050505060405180910390f35b34801561015f57600080fd5b506101a36004803603608081101561017657600080fd5b50803590602081013563ffffffff90811691604081013590911690606001356001600160a01b0316610512565b005b6101a3600480360360208110156101bb57600080fd5b503563ffffffff166106b8565b3480156101d457600080fd5b506101a3600480360360208110156101eb57600080fd5b503563ffffffff1661088c565b34801561020457600080fd5b506102d5600480360360a081101561021b57600080fd5b81359163ffffffff602082013516916001600160a01b036040830135169160ff6060820135169181019060a08101608082013564010000000081111561026057600080fd5b82018360208201111561027257600080fd5b8035906020019184600183028401116401000000008311171561029457600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610b09945050505050565b604080519115158252519081900360200190f35b3480156102f557600080fd5b506101a36004803603604081101561030c57600080fd5b50803563ffffffff1690602001356001600160a01b0316610fad565b34801561033457600080fd5b506103586004803603602081101561034b57600080fd5b503563ffffffff16611138565b604080516001600160a01b03909316835263ffffffff90911660208301528051918290030190f35b34801561038c57600080fd5b506102d5600480360360208110156103a357600080fd5b5035611162565b3480156103b657600080fd5b506101a3600480360360e08110156103cd57600080fd5b5080359060208101359060408101356001600160a01b039081169160608101358216916080820135169060a081013563ffffffff169060c0013560ff16611175565b34801561041b57600080fd5b506104246114da565b604080516001600160a01b039092168252519081900360200190f35b34801561044c57600080fd5b506101a36004803603604081101561046357600080fd5b50803560ff1690602001356001600160a01b03166114e9565b34801561048857600080fd5b506104ac6004803603602081101561049f57600080fd5b503563ffffffff166115d3565b60408051918252519081900360200190f35b63ffffffff9081166000908152603760205260409020805460018201546002830154600390930154919490936001600160a01b0393841693831692600160a01b810490911691600160c01b90910460ff1690565b6033546001600160a01b0316331461055b5760405162461bcd60e51b8152600401808060200182810382526021815260200180611ab76021913960400191505060405180910390fd5b63ffffffff8381166000908152603a6020526040902054818416600160a01b909104909116106105bc5760405162461bcd60e51b8152600401808060200182810382526031815260200180611b936031913960400191505060405180910390fd5b6105c5846115e5565b61060d576040805162461bcd60e51b8152602060048201526014602482015273151608185b1c9958591e481c1c9bd8d95cdcd95960621b604482015290519081900360640190fd5b6040805180820182526001600160a01b0383811680835263ffffffff86811660208086019182528983166000818152603a83528890209651875493516001600160a01b031990941696169590951763ffffffff60a01b1916600160a01b92909316919091029190911790935583519182529181019190915281517f5276cc41288d98dae7d6e7ca6412b8335adfb3bd319f2e1dfc3933901d173033929181900390910190a150505050565b63ffffffff8116600090815260376020526040902060016003820154600160c01b900460ff1660048111156106e957fe5b146107255760405162461bcd60e51b81526004018080602001828103825260528152602001806119186052913960600191505060405180910390fd5b60038101546001600160a01b031633146107705760405162461bcd60e51b8152600401808060200182810382526052815260200180611a656052913960600191505060405180910390fd5b600060395460ff16600281111561078357fe5b1461079057618ca0610795565b620dd7c05b81544203116107d55760405162461bcd60e51b8152600401808060200182810382526054815260200180611a116054913960600191505060405180910390fd5b6729a2241af62c000034101561081c5760405162461bcd60e51b81526004018080602001828103825260468152602001806119cb6046913960600191505060405180910390fd5b63ffffffff821660008181526038602090815260409182902034905542845560038401805460ff60c01b1916600160c11b17905581513381529081019290925280517f1bd938c0559acc36703807b71652dec64b2eed0d54f1716803e65cedc4f55a129281900390910190a15050565b63ffffffff8116600090815260376020526040902060026003820154600160c01b900460ff1660048111156108bd57fe5b146108f95760405162461bcd60e51b815260040180806020018281038252604c815260200180611bc4604c913960600191505060405180910390fd5b610e10816000015442031161093f5760405162461bcd60e51b8152600401808060200182810382526048815260200180611b4b6048913960600191505060405180910390fd5b60038101805460ff60c01b1916600160c21b17908190556002820154600183015463ffffffff600160a01b90930483166000908152603460205260409020546001600160a01b039092169261099692919061161d16565b600383018054600160a01b900463ffffffff1660009081526034602090815260408083209490945591546001860154845163a9059cbb60e01b81526001600160a01b039283166004820152602481019190915293519085169363a9059cbb936044808301949193928390030190829087803b158015610a1457600080fd5b505af1158015610a28573d6000803e3d6000fd5b505050506040513d6020811015610a3e57600080fd5b5050600382015463ffffffff841660009081526038602052604080822080549083905590516001600160a01b039093169290918391839181818185875af1925050503d8060008114610aac576040519150601f19603f3d011682016040523d82523d6000602084013e610ab1565b606091505b5050506003840154604080516001600160a01b03909216825263ffffffff8716602083015280517f558dcc0f85e822d51fb0c98b95ab299d76c136c9d1a34b9cb2e3ede1689cdcfe9281900390910190a15050505050565b600083866000826001600160a01b031663313ce5676040518163ffffffff1660e01b815260040160206040518083038186803b158015610b4857600080fd5b505afa158015610b5c573d6000803e3d6000fd5b505050506040513d6020811015610b7257600080fd5b505160ff169050610b8d600a82810a9063ffffffff61166616565b821015610bcb5760405162461bcd60e51b815260040180806020018281038252602c8152602001806118ec602c913960400191505060405180910390fd5b6000855111610c21576040805162461bcd60e51b815260206004820152601d60248201527f737973636f696e416464726573732063616e6e6f74206265207a65726f000000604482015290519081900360640190fd5b60008863ffffffff1611610c7c576040805162461bcd60e51b815260206004820152601860248201527f41737365742047554944206d757374206e6f7420626520300000000000000000604482015290519081900360640190fd5b600260395460ff166002811115610c8f57fe5b14610cf35763ffffffff88166000908152603a60205260409020546001600160a01b03888116911614610cf35760405162461bcd60e51b8152600401808060200182810382526045815260200180611b066045913960600191505060405180910390fd5b6000879050806001600160a01b031663313ce5676040518163ffffffff1660e01b815260040160206040518083038186803b158015610d3157600080fd5b505afa158015610d45573d6000803e3d6000fd5b505050506040513d6020811015610d5b57600080fd5b505160ff888116911614610da05760405162461bcd60e51b81526004018080602001828103825260318152602001806118bb6031913960400191505060405180910390fd5b604080516323b872dd60e01b8152336004820152306024820152604481018c905290516001600160a01b038316916323b872dd9160648083019260209291908290030181600087803b158015610df557600080fd5b505af1158015610e09573d6000803e3d6000fd5b505050506040513d6020811015610e1f57600080fd5b505063ffffffff808a16600090815260346020526040902054610e44918c906116a816565b63ffffffff808b166000818152603460209081526040918290209490945560368054808516600190810190951663ffffffff19909116179055805160c0810182524281529384018e90526001600160a01b038c1690840152336060840152608083015260a082015260365463ffffffff90811660009081526037602090815260409182902084518155908401516001820155908301516002820180546001600160a01b03199081166001600160a01b0393841617909155606085015160038401805460808801519316919093161763ffffffff60a01b1916600160a01b91909416029290921780835560a084015191929060ff60c01b1916600160c01b836004811115610f4d57fe5b02179055505060365460408051338152602081018e905263ffffffff90921682820152517faabab1db49e504b5156edf3f99042aeecb9607a08f392589571cd49743aaba8d92509081900360600190a15060019998505050505050505050565b6033546001600160a01b03163314610ff65760405162461bcd60e51b8152600401808060200182810382526021815260200180611ab76021913960400191505060405180910390fd5b63ffffffff8216600090815260376020526040902060026003820154600160c01b900460ff16600481111561102757fe5b146110635760405162461bcd60e51b815260040180806020018281038252606181526020018061196a6061913960800191505060405180910390fd5b60038101805460ff60c01b1916600360c01b17905563ffffffff8316600090815260386020526040808220805490839055905190916001600160a01b03851691839181818185875af1925050503d80600081146110dc576040519150601f19603f3d011682016040523d82523d6000602084013e6110e1565b606091505b5050506003820154604080516001600160a01b03909216825263ffffffff8616602083015280517f960e217c57581c52cdc4e321eb617416d051a348a2ecf62bb8023a3558e80e859281900390910190a150505050565b603a602052600090815260409020546001600160a01b03811690600160a01b900463ffffffff1682565b600061116d82611702565b90505b919050565b6033546001600160a01b031633146111be5760405162461bcd60e51b8152600401808060200182810382526021815260200180611ab76021913960400191505060405180910390fd5b60008390506000816001600160a01b031663313ce5676040518163ffffffff1660e01b815260040160206040518083038186803b1580156111fe57600080fd5b505afa158015611212573d6000803e3d6000fd5b505050506040513d602081101561122857600080fd5b5051905060ff808416908216111561124c5782810360ff16600a0a8802975061126f565b8260ff168160ff16101561126f5780830360ff16600a0a888161126b57fe5b0497505b6112798189611717565b611282896115e5565b6112ca576040805162461bcd60e51b8152602060048201526014602482015273151608185b1c9958591e481c1c9bd8d95cdcd95960621b604482015290519081900360640190fd5b63ffffffff8085166000908152603460205260409020546112ed918a9061161d16565b63ffffffff808616600090815260346020526040812092909255611317908a906127109061166616565b9050600061132b8a8363ffffffff61161d16565b9050836001600160a01b031663a9059cbb89846040518363ffffffff1660e01b815260040180836001600160a01b03166001600160a01b0316815260200182815260200192505050602060405180830381600087803b15801561138d57600080fd5b505af11580156113a1573d6000803e3d6000fd5b505050506040513d60208110156113b757600080fd5b5050604080516001600160a01b038a1681526020810184905281517f378dbe173f6ed6e11630b29573f719ec4cefc9b49f430deed915911c5f78a080929181900390910190a1836001600160a01b031663a9059cbb8a836040518363ffffffff1660e01b815260040180836001600160a01b03166001600160a01b0316815260200182815260200192505050602060405180830381600087803b15801561145d57600080fd5b505af1158015611471573d6000803e3d6000fd5b505050506040513d602081101561148757600080fd5b5050604080516001600160a01b038b1681526020810183905281517fb925ba840e2f36bcb317f8179bd8b5ed01aba4a22abf5f169162c0894dea87ab929181900390910190a15050505050505050505050565b6033546001600160a01b031681565b600054610100900460ff168061150257506115026117b8565b80611510575060005460ff16155b61154b5760405162461bcd60e51b815260040180806020018281038252602e815260200180611ad8602e913960400191505060405180910390fd5b600054610100900460ff16158015611576576000805460ff1961ff0019909116610100171660011790555b6039805484919060ff1916600183600281111561158f57fe5b0217905550603380546001600160a01b0319166001600160a01b0384161790556036805463ffffffff1916905580156115ce576000805461ff00191690555b505050565b60346020526000908152604090205481565b60006115f082611702565b156115fd57506000611170565b506000908152603560205260409020805460ff1916600190811790915590565b600061165f83836040518060400160405280601e81526020017f536166654d6174683a207375627472616374696f6e206f766572666c6f7700008152506117be565b9392505050565b600061165f83836040518060400160405280601a81526020017f536166654d6174683a206469766973696f6e206279207a65726f000000000000815250611855565b60008282018381101561165f576040805162461bcd60e51b815260206004820152601b60248201527f536166654d6174683a206164646974696f6e206f766572666c6f770000000000604482015290519081900360640190fd5b60009081526035602052604090205460ff1690565b60ff821681611766576040805162461bcd60e51b815260206004820152601660248201527556616c7565206d75737420626520706f73697469766560501b604482015290519081900360640190fd5b61177a600a82810a9063ffffffff61166616565b8210156115ce5760405162461bcd60e51b815260040180806020018281038252602c8152602001806118ec602c913960400191505060405180910390fd5b303b1590565b6000818484111561184d5760405162461bcd60e51b81526004018080602001828103825283818151815260200191508051906020019080838360005b838110156118125781810151838201526020016117fa565b50505050905090810190601f16801561183f5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b505050900390565b600081836118a45760405162461bcd60e51b81526020600482018181528351602484015283519092839260449091019190850190808383600083156118125781810151838201526020016117fa565b5060008385816118b057fe5b049594505050505056fe446563696d616c732077657265206e6f742070726f766964656420776974682074686520636f72726563742076616c756556616c7565206d75737420626520626967676572206f7220657175616c204d494e5f4c4f434b5f56414c554523537973636f696e45524332304d616e616765722063616e63656c5472616e736665725265717565737428293a20537461747573206f6620627269646765207472616e73666572206d757374206265204f6b23537973636f696e45524332304d616e616765722063616e63656c5472616e736665725375636365737328293a20537461747573206d7573742062652043616e63656c52657175657374656420746f204661696c20746865207472616e7366657223537973636f696e45524332304d616e616765722063616e63656c5472616e736665725265717565737428293a2043616e63656c206465706f73697420696e636f727265637423537973636f696e45524332304d616e616765722063616e63656c5472616e736665725265717565737428293a205472616e73666572206d757374206265206174206c6561737420312e35207765656b206f6c6423537973636f696e45524332304d616e616765722063616e63656c5472616e736665725265717565737428293a204f6e6c79206d73672e73656e64657220697320616c6c6f77656420746f2063616e63656c43616c6c206d7573742062652066726f6d20747275737465642072656c61796572436f6e747261637420696e7374616e63652068617320616c7265616479206265656e20696e697469616c697a6564417373657420726567697374727920636f6e747261637420646f6573206e6f74206d617463682077686174207761732070726f766964656420746f20746869732063616c6c23537973636f696e45524332304d616e616765722063616e63656c5472616e736665725375636365737328293a203120686f75722074696d656f7574206973207265717569726564486569676874206d75737420696e637265617365207768656e207570646174696e6720617373657420726567697374727923537973636f696e45524332304d616e616765722063616e63656c5472616e736665725375636365737328293a20537461747573206d7573742062652043616e63656c526571756573746564a265627a7a7231582099ea44a9921de12f4ff3496ffa8c167e7900cff9126a6af263daa5d8e1a2550864736f6c634300050d0032";

    public static final String FUNC_ASSETBALANCES = "assetBalances";

    public static final String FUNC_ASSETREGISTRY = "assetRegistry";

    public static final String FUNC_TRUSTEDRELAYERCONTRACT = "trustedRelayerContract";

    public static final String FUNC_INIT = "init";

    public static final String FUNC_WASSYSCOINTXPROCESSED = "wasSyscoinTxProcessed";

    public static final String FUNC_PROCESSTRANSACTION = "processTransaction";

    public static final String FUNC_PROCESSASSET = "processAsset";

    public static final String FUNC_CANCELTRANSFERREQUEST = "cancelTransferRequest";

    public static final String FUNC_CANCELTRANSFERSUCCESS = "cancelTransferSuccess";

    public static final String FUNC_PROCESSCANCELTRANSFERFAIL = "processCancelTransferFail";

    public static final String FUNC_FREEZEBURNERC20 = "freezeBurnERC20";

    public static final String FUNC_GETBRIDGETRANSFER = "getBridgeTransfer";

    public static final Event CANCELTRANSFERFAILED_EVENT = new Event("CancelTransferFailed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint32>() {}));
    ;

    public static final Event CANCELTRANSFERREQUEST_EVENT = new Event("CancelTransferRequest", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint32>() {}));
    ;

    public static final Event CANCELTRANSFERSUCCEEDED_EVENT = new Event("CancelTransferSucceeded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint32>() {}));
    ;

    public static final Event TOKENFREEZE_EVENT = new Event("TokenFreeze", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint32>() {}));
    ;

    public static final Event TOKENREGISTRY_EVENT = new Event("TokenRegistry", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event TOKENUNFREEZE_EVENT = new Event("TokenUnfreeze", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TOKENUNFREEZEFEE_EVENT = new Event("TokenUnfreezeFee", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
         _addresses.put("1", "0xFF957eA28b537b34E0c6E6B50c6c938668DD28a0");
    }

    @Deprecated
    protected SyscoinERC20Manager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SyscoinERC20Manager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SyscoinERC20Manager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SyscoinERC20Manager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<CancelTransferFailedEventResponse> getCancelTransferFailedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CANCELTRANSFERFAILED_EVENT, transactionReceipt);
        ArrayList<CancelTransferFailedEventResponse> responses = new ArrayList<CancelTransferFailedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CancelTransferFailedEventResponse typedResponse = new CancelTransferFailedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.canceller = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.bridgetransferid = (Uint32) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CancelTransferFailedEventResponse> cancelTransferFailedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CancelTransferFailedEventResponse>() {
            @Override
            public CancelTransferFailedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CANCELTRANSFERFAILED_EVENT, log);
                CancelTransferFailedEventResponse typedResponse = new CancelTransferFailedEventResponse();
                typedResponse.log = log;
                typedResponse.canceller = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.bridgetransferid = (Uint32) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<CancelTransferFailedEventResponse> cancelTransferFailedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CANCELTRANSFERFAILED_EVENT));
        return cancelTransferFailedEventFlowable(filter);
    }

    public List<CancelTransferRequestEventResponse> getCancelTransferRequestEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CANCELTRANSFERREQUEST_EVENT, transactionReceipt);
        ArrayList<CancelTransferRequestEventResponse> responses = new ArrayList<CancelTransferRequestEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CancelTransferRequestEventResponse typedResponse = new CancelTransferRequestEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.canceller = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.bridgetransferid = (Uint32) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CancelTransferRequestEventResponse> cancelTransferRequestEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CancelTransferRequestEventResponse>() {
            @Override
            public CancelTransferRequestEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CANCELTRANSFERREQUEST_EVENT, log);
                CancelTransferRequestEventResponse typedResponse = new CancelTransferRequestEventResponse();
                typedResponse.log = log;
                typedResponse.canceller = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.bridgetransferid = (Uint32) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<CancelTransferRequestEventResponse> cancelTransferRequestEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CANCELTRANSFERREQUEST_EVENT));
        return cancelTransferRequestEventFlowable(filter);
    }

    public List<CancelTransferSucceededEventResponse> getCancelTransferSucceededEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CANCELTRANSFERSUCCEEDED_EVENT, transactionReceipt);
        ArrayList<CancelTransferSucceededEventResponse> responses = new ArrayList<CancelTransferSucceededEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CancelTransferSucceededEventResponse typedResponse = new CancelTransferSucceededEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.canceller = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.bridgetransferid = (Uint32) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CancelTransferSucceededEventResponse> cancelTransferSucceededEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CancelTransferSucceededEventResponse>() {
            @Override
            public CancelTransferSucceededEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CANCELTRANSFERSUCCEEDED_EVENT, log);
                CancelTransferSucceededEventResponse typedResponse = new CancelTransferSucceededEventResponse();
                typedResponse.log = log;
                typedResponse.canceller = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.bridgetransferid = (Uint32) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<CancelTransferSucceededEventResponse> cancelTransferSucceededEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CANCELTRANSFERSUCCEEDED_EVENT));
        return cancelTransferSucceededEventFlowable(filter);
    }

    public List<TokenFreezeEventResponse> getTokenFreezeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENFREEZE_EVENT, transactionReceipt);
        ArrayList<TokenFreezeEventResponse> responses = new ArrayList<TokenFreezeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokenFreezeEventResponse typedResponse = new TokenFreezeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.freezer = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.bridgetransferid = (Uint32) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokenFreezeEventResponse> tokenFreezeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokenFreezeEventResponse>() {
            @Override
            public TokenFreezeEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKENFREEZE_EVENT, log);
                TokenFreezeEventResponse typedResponse = new TokenFreezeEventResponse();
                typedResponse.log = log;
                typedResponse.freezer = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse.bridgetransferid = (Uint32) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<TokenFreezeEventResponse> tokenFreezeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENFREEZE_EVENT));
        return tokenFreezeEventFlowable(filter);
    }

    public List<TokenRegistryEventResponse> getTokenRegistryEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENREGISTRY_EVENT, transactionReceipt);
        ArrayList<TokenRegistryEventResponse> responses = new ArrayList<TokenRegistryEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokenRegistryEventResponse typedResponse = new TokenRegistryEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.assetGuid = (Uint32) eventValues.getNonIndexedValues().get(0);
            typedResponse.erc20ContractAddress = (Address) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokenRegistryEventResponse> tokenRegistryEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokenRegistryEventResponse>() {
            @Override
            public TokenRegistryEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKENREGISTRY_EVENT, log);
                TokenRegistryEventResponse typedResponse = new TokenRegistryEventResponse();
                typedResponse.log = log;
                typedResponse.assetGuid = (Uint32) eventValues.getNonIndexedValues().get(0);
                typedResponse.erc20ContractAddress = (Address) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<TokenRegistryEventResponse> tokenRegistryEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENREGISTRY_EVENT));
        return tokenRegistryEventFlowable(filter);
    }

    public List<TokenUnfreezeEventResponse> getTokenUnfreezeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENUNFREEZE_EVENT, transactionReceipt);
        ArrayList<TokenUnfreezeEventResponse> responses = new ArrayList<TokenUnfreezeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokenUnfreezeEventResponse typedResponse = new TokenUnfreezeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.receipient = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokenUnfreezeEventResponse> tokenUnfreezeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokenUnfreezeEventResponse>() {
            @Override
            public TokenUnfreezeEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKENUNFREEZE_EVENT, log);
                TokenUnfreezeEventResponse typedResponse = new TokenUnfreezeEventResponse();
                typedResponse.log = log;
                typedResponse.receipient = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<TokenUnfreezeEventResponse> tokenUnfreezeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENUNFREEZE_EVENT));
        return tokenUnfreezeEventFlowable(filter);
    }

    public List<TokenUnfreezeFeeEventResponse> getTokenUnfreezeFeeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENUNFREEZEFEE_EVENT, transactionReceipt);
        ArrayList<TokenUnfreezeFeeEventResponse> responses = new ArrayList<TokenUnfreezeFeeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokenUnfreezeFeeEventResponse typedResponse = new TokenUnfreezeFeeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.receipient = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokenUnfreezeFeeEventResponse> tokenUnfreezeFeeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokenUnfreezeFeeEventResponse>() {
            @Override
            public TokenUnfreezeFeeEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKENUNFREEZEFEE_EVENT, log);
                TokenUnfreezeFeeEventResponse typedResponse = new TokenUnfreezeFeeEventResponse();
                typedResponse.log = log;
                typedResponse.receipient = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<TokenUnfreezeFeeEventResponse> tokenUnfreezeFeeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENUNFREEZEFEE_EVENT));
        return tokenUnfreezeFeeEventFlowable(filter);
    }

    public RemoteFunctionCall<Uint256> assetBalances(Uint32 param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ASSETBALANCES, 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Tuple2<Address, Uint32>> assetRegistry(Uint32 param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ASSETREGISTRY, 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint32>() {}));
        return new RemoteFunctionCall<Tuple2<Address, Uint32>>(function,
                new Callable<Tuple2<Address, Uint32>>() {
                    @Override
                    public Tuple2<Address, Uint32> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<Address, Uint32>(
                                (Address) results.get(0), 
                                (Uint32) results.get(1));
                    }
                });
    }

    public RemoteFunctionCall<Address> trustedRelayerContract() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TRUSTEDRELAYERCONTRACT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<TransactionReceipt> init(Uint8 _network, Address _trustedRelayerContract) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INIT, 
                Arrays.<Type>asList(_network, _trustedRelayerContract), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Bool> wasSyscoinTxProcessed(Uint256 txHash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_WASSYSCOINTXPROCESSED, 
                Arrays.<Type>asList(txHash), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<TransactionReceipt> processTransaction(Uint256 txHash, Uint256 value, Address destinationAddress, Address superblockSubmitterAddress, Address erc20ContractAddress, Uint32 assetGUID, Uint8 precision) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PROCESSTRANSACTION, 
                Arrays.<Type>asList(txHash, value, destinationAddress, superblockSubmitterAddress, erc20ContractAddress, assetGUID, precision), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> processAsset(Uint256 _txHash, Uint32 _assetGUID, Uint32 _height, Address _erc20ContractAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PROCESSASSET, 
                Arrays.<Type>asList(_txHash, _assetGUID, _height, _erc20ContractAddress), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelTransferRequest(Uint32 bridgeTransferId, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CANCELTRANSFERREQUEST, 
                Arrays.<Type>asList(bridgeTransferId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelTransferSuccess(Uint32 bridgeTransferId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CANCELTRANSFERSUCCESS, 
                Arrays.<Type>asList(bridgeTransferId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> processCancelTransferFail(Uint32 bridgeTransferId, Address challengerAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PROCESSCANCELTRANSFERFAIL, 
                Arrays.<Type>asList(bridgeTransferId, challengerAddress), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> freezeBurnERC20(Uint256 value, Uint32 assetGUID, Address erc20ContractAddress, Uint8 precision, DynamicBytes syscoinAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FREEZEBURNERC20, 
                Arrays.<Type>asList(value, assetGUID, erc20ContractAddress, precision, syscoinAddress), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple6<Uint256, Uint256, Address, Address, Uint32, Uint8>> getBridgeTransfer(Uint32 bridgeTransferId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETBRIDGETRANSFER, 
                Arrays.<Type>asList(bridgeTransferId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint8>() {}));
        return new RemoteFunctionCall<Tuple6<Uint256, Uint256, Address, Address, Uint32, Uint8>>(function,
                new Callable<Tuple6<Uint256, Uint256, Address, Address, Uint32, Uint8>>() {
                    @Override
                    public Tuple6<Uint256, Uint256, Address, Address, Uint32, Uint8> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<Uint256, Uint256, Address, Address, Uint32, Uint8>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Address) results.get(2), 
                                (Address) results.get(3), 
                                (Uint32) results.get(4), 
                                (Uint8) results.get(5));
                    }
                });
    }

    @Deprecated
    public static SyscoinERC20Manager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SyscoinERC20Manager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SyscoinERC20Manager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SyscoinERC20Manager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SyscoinERC20Manager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SyscoinERC20Manager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SyscoinERC20Manager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SyscoinERC20Manager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SyscoinERC20Manager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SyscoinERC20Manager.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SyscoinERC20Manager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SyscoinERC20Manager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SyscoinERC20Manager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SyscoinERC20Manager.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<SyscoinERC20Manager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SyscoinERC20Manager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class CancelTransferFailedEventResponse extends BaseEventResponse {
        public Address canceller;

        public Uint32 bridgetransferid;
    }

    public static class CancelTransferRequestEventResponse extends BaseEventResponse {
        public Address canceller;

        public Uint32 bridgetransferid;
    }

    public static class CancelTransferSucceededEventResponse extends BaseEventResponse {
        public Address canceller;

        public Uint32 bridgetransferid;
    }

    public static class TokenFreezeEventResponse extends BaseEventResponse {
        public Address freezer;

        public Uint256 value;

        public Uint32 bridgetransferid;
    }

    public static class TokenRegistryEventResponse extends BaseEventResponse {
        public Uint32 assetGuid;

        public Address erc20ContractAddress;
    }

    public static class TokenUnfreezeEventResponse extends BaseEventResponse {
        public Address receipient;

        public Uint256 value;
    }

    public static class TokenUnfreezeFeeEventResponse extends BaseEventResponse {
        public Address receipient;

        public Uint256 value;
    }
}
