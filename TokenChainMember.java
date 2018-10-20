public interface TokenChainMember {

	public abstract void setUpstream(TokenChainMember tcm);
	public abstract boolean hasToken();
	public abstract String nextToken();

}