package org.torproject.jtor.crypto;

public class TorCreateFastKeyAgreement {
	
	private final byte[] xValue;
	private byte[] yValue;

	public TorCreateFastKeyAgreement() {
		final TorRandom random = new TorRandom();
		xValue = random.getBytes(TorMessageDigest.TOR_DIGEST_SIZE);
	}
	
	public byte[] getPublicValue() {
		return xValue;
	}

	public void setOtherValue(byte[] yValue) {
		if(yValue == null || yValue.length != TorMessageDigest.TOR_DIGEST_SIZE) {
			throw new IllegalArgumentException();
		}
		this.yValue = yValue;
	}
	
	public byte[] getDerivedValue() {
		if(yValue == null) {
			throw new IllegalStateException("Must call setOtherValue() first");
		}
		final byte[] result = new byte[2 * TorMessageDigest.TOR_DIGEST_SIZE];
		System.arraycopy(xValue, 0, result, 0, TorMessageDigest.TOR_DIGEST_SIZE);
		System.arraycopy(yValue, 0, result, TorMessageDigest.TOR_DIGEST_SIZE, TorMessageDigest.TOR_DIGEST_SIZE);
		return result;
	}
}
